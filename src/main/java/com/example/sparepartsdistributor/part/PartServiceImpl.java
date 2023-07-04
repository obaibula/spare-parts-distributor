package com.example.sparepartsdistributor.part;

import com.example.sparepartsdistributor.exception.PartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<PartDto> findAll(Pageable pageable) {
        return partRepository.findAll(pageable)
                .map(PartDto::partToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PartDto findById(Long partId) {
        return partRepository.findById(partId)
                .map(PartDto::partToDto)
                .orElseThrow(() -> new PartNotFoundException("Part not found with id - " + partId));
    }
}
