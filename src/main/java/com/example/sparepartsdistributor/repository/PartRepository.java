package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing spare parts.
 * Provided CRUD operations and query methods for the {@link Part} entity.
 */
public interface PartRepository extends JpaRepository<Part, Long> {
}
