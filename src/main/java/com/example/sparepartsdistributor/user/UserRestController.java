package com.example.sparepartsdistributor.user;

import com.example.sparepartsdistributor.user.dto.UserDto;
import com.example.sparepartsdistributor.user.dto.UserRequestDTO;
import jakarta.validation.Valid;
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
     * Creates a new userRequest based on the provided request body.
     * The created userRequest is returned in the response with the appropriate HTTP status and location header.
     *
     * @param userRequest the userRequest to be created, as specified in the request body
     * @return the ResponseEntity containing the created userRequest and location URI
     */
    @PostMapping
    private ResponseEntity<UserDto> createUser(@RequestBody @Valid UserRequestDTO userRequest){
        var savedUser = userService.save(userRequest);
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
    private URI getLocation(UserDto user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.id())
                .toUri();
    }
}
