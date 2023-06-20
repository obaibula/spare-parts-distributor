package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
