package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    @Override
    public Part save(Part part) {
        return partRepository.save(part);
    }
}
