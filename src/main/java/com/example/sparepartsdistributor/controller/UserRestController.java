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

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody User user){
        User addedUser = userService.save(user);

        return created(getLocation(addedUser))
                .body(addedUser);
    }

    private URI getLocation(User addedUser) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedUser.getId())
                .toUri();
    }
}
