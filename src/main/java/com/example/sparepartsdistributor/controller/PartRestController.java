package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

}
