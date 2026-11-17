package com.klef.tourist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.tourist.entity.Tourist;
import com.klef.tourist.repository.TouristRepository;

@Service
public class TouristServiceImpl implements TouristService {

    private final TouristRepository repository;

    public TouristServiceImpl(TouristRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tourist addTourist(Tourist tourist) {
        tourist.setId(null);
        return repository.save(tourist);
    }

    @Override
    public List<Tourist> getAllTourists() {
        return repository.findAll();
    }

    @Override
    public Tourist getTouristById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Tourist not found"));
    }

    @Override
    public Tourist updateTourist(Long id, Tourist tourist) {

        Tourist existing = getTouristById(id);

        existing.setName(tourist.getName());
        existing.setLocation(tourist.getLocation());
        existing.setPrice(tourist.getPrice());
        existing.setDescription(tourist.getDescription());

        return repository.save(existing);
    }

    @Override
    public String deleteTourist(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Tourist not found");
        }

        repository.deleteById(id);

        return "Tourist deleted successfully";
    }
}