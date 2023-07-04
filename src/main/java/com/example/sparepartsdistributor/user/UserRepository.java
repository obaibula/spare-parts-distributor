package com.example.sparepartsdistributor.user;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing users.
 * Provided CRUD operations and query methods for the {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
