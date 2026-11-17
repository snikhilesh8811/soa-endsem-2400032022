package com.klef.tourist.service;

import java.util.List;
import com.klef.tourist.entity.Tourist;

public interface TouristService {

    Tourist addTourist(Tourist tourist);

    List<Tourist> getAllTourists();

    Tourist getTouristById(Long id);

    Tourist updateTourist(Long id, Tourist tourist);

    String deleteTourist(Long id);
}