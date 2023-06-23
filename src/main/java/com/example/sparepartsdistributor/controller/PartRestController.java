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
    private ResponseEntity<Part> createPart(@RequestBody Part part) {
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

    /**
     * Retrieves all parts with pagination and generates HATEOAS links.
     *
     * @param pageable the pagination information
     * @return the Collection model of Entity model of PartDto containing parts and self links
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<PartDto>> all(Pageable pageable) {
        var parts = partService.findAll(createPageRequest(pageable))
                .stream()
                .map(part -> getPartDtoEntityModel(pageable, part))
                .toList();

        return CollectionModel.of(
                parts,
                getLinkToAllMethod(pageable)
                        .withSelfRel());
    }

    /**
     * Creates an EntityModel of PartDto with HATEOAS links.
     *
     * @param pageable the pagination information
     * @param part     the PartDto object
     * @return the EntityModel of PartDto with links
     */
    private EntityModel<PartDto> getPartDtoEntityModel(Pageable pageable, PartDto part) {
        return EntityModel.of(part,
                getLinkToOneMethod(part.id()).withSelfRel(),
                getLinkToAllMethod(pageable).withRel("parts"));
    }

    /**
     * Creates a PageRequest object for pagination.
     *
     * @param pageable the original Pageable object
     * @return the created PageRequest object
     */
    private PageRequest createPageRequest(Pageable pageable) {
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id")));
    }

    /**
     * Retrieves a single part with the specified partId and generates HATEOAS links.
     *
     * @param partId the ID of the part to retrieve
     * @return the EntityModel of PartDto containing the part and related links
     */
    @GetMapping("/{partId}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<PartDto> one(@PathVariable Long partId) {
        PartDto partDto = partService.findById(partId);
        return EntityModel.of(partDto,
                getLinkToOneMethod(partId).withSelfRel(),
                getLinkToAllMethod(Pageable.unpaged()).withRel("parts"));
    }

    /**
     * Generates the link to the "one" method for the specified partId.
     *
     * @param partId the ID of the part
     * @return the WebMvcLinkBuilder for the "one" method
     */
    private WebMvcLinkBuilder getLinkToOneMethod(Long partId) {
        return linkTo(methodOn(PartRestController.class).one(partId));
    }

    /**
     * Generates the link to the "all" method for pagination.
     *
     * @param pageable the pagination information
     * @return the WebMvcLinkBuilder for the "all" method
     */
    private WebMvcLinkBuilder getLinkToAllMethod(Pageable pageable) {
        return linkTo(methodOn(PartRestController.class).all(pageable));
    }

}
