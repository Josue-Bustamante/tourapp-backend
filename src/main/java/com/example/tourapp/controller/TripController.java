package com.example.tourapp.controller;

import com.example.tourapp.model.Trip;
import com.example.tourapp.service.ITripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final ITripService service;

    @GetMapping
    private ResponseEntity<List<Trip>> findAll() throws Exception {
        List<Trip> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Trip> findById(@PathVariable Integer id) throws Exception {
        Trip obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Trip> save(@RequestBody Trip trip) throws Exception {
        Trip obj = service.save(trip);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Trip> update(@PathVariable Integer id, @RequestBody Trip trip) throws Exception {
        Trip obj = service.update(id, trip);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



