package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.dto.PartDto;
import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * REST controller class that handles requests related to spare parts.
 */
@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class PartRestController {

    private final PartService partService;

    /**
     * Creates a new part based on the provided request body.
     * The created part is returned in the response with the appropriate HTTP status and location header.
     *
     * @param part the part to be created, as specified in the request body
     * @return the ResponseEntity containing the created part and location URI
     */
    @PostMapping
    private ResponseEntity<Part> createPart(@RequestBody Part part){
        var savedPart = partService.save(part);

        return ResponseEntity
                .created(getLocation(savedPart))
                .body(savedPart);
    }

    /**
     * Generates the location URI for the specified part.
     * The location URI is based on the current request URI and the ID of the part.
     *
     * @param part the part for which the location URI is generated
     * @return the generated location URI for the part
     */
    private URI getLocation(Part part) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(part.getId())
                .toUri();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<PartDto>> all(Pageable pageable){
        var parts = partService.findAll(createPageRequest(pageable))
                .stream()
                .map(part -> getPartDtoEntityModel(pageable, part))
                .toList();

        return CollectionModel.of(
                parts,
                getLinkToAllMethod(pageable)
                .withSelfRel());
    }

    private EntityModel<PartDto> getPartDtoEntityModel(Pageable pageable, PartDto part) {
        return EntityModel.of(part, getLinkToAllMethod(pageable).withRel("parts"));
    }

    private WebMvcLinkBuilder getLinkToAllMethod(Pageable pageable) {
        return linkTo(methodOn(PartRestController.class).all(pageable));
    }

    private PageRequest createPageRequest(Pageable pageable) {
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id")));
    }

}
