package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the {@link PartService} interface that provides functionality for managing spare parts.
 * This class uses the {@link PartRepository} to interact with the underlying data store
 */
@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    @Override
    @Transactional
    public Part save(Part part) {
        return partRepository.save(part);
    }
}
