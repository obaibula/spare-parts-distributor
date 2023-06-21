package com.example.sparepartsdistributor.controller;

import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

/**
 * REST controller class that handles requests related to users.
 */
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    /**
     * Creates a new user based on the provided request body.
     * The created user is returned in the response with the appropriate HTTP status and location header.
     *
     * @param user the user to be created, as specified in the request body
     * @return the ResponseEntity containing the created user and location URI
     */
    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.save(user);

        return created(getLocation(savedUser))
                .body(savedUser);
    }

    /**
     * Generates the location URI for the specified user.
     * The location URI is based on the current request URI and the ID of the user.
     *
     * @param user the user for which the location URI is generated
     * @return the generated location URI for the user
     */
    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
    }
}
