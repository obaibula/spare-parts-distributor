package com.example.sparepartsdistributor.repository;

import com.example.sparepartsdistributor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
