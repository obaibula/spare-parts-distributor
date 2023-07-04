package com.example.sparepartsdistributor.part;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing spare parts.
 * Provided CRUD operations and query methods for the {@link Part} entity.
 */
public interface PartRepository extends JpaRepository<Part, Long> {
}
