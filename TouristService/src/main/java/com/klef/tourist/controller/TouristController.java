package com.klef.tourist.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.tourist.entity.Tourist;
import com.klef.tourist.service.TouristService;

@RestController
@RequestMapping("/tourist")
@CrossOrigin
public class TouristController {

    private final TouristService service;

    @Value("${server.port}")
    private String port;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tourist> addTourist(
            @RequestBody Tourist tourist) {

        return ResponseEntity.status(201)
                .body(service.addTourist(tourist));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTourists() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Tourists retrieved successfully");
        response.put("instancePort", port);
        response.put("status", "SUCCESS");
        response.put("tourists", service.getAllTourists());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tourist> getTouristById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getTouristById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tourist> updateTourist(
            @PathVariable Long id,
            @RequestBody Tourist tourist) {

        return ResponseEntity.ok(
                service.updateTourist(id, tourist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTourist(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.deleteTourist(id));
    }

    // Important for load balancing demonstration
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok(
                "Tourist Service running on port: " + port);
    }
}