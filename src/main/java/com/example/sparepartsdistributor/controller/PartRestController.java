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

@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class PartRestController {

    private final PartService partService;

    @PostMapping
    private ResponseEntity<Part> createPart(@RequestBody Part part){
        var savedPart = partService.save(part);

        return ResponseEntity
                .created(getLocation(savedPart))
                .body(savedPart);
    }

    private URI getLocation(Part part) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(part.getId())
                .toUri();
    }

}
