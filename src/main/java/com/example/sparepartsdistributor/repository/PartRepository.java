package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
