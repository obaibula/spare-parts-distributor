package com.example.sparepartsdistributor.repository;


import com.example.sparepartsdistributor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing users.
 * Provided CRUD operations and query methods for the {@link User} entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
