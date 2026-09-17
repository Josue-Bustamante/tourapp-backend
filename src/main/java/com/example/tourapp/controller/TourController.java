package com.example.tourapp.controller;

import com.example.tourapp.model.Tour;
import com.example.tourapp.service.ITourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tours")
@RequiredArgsConstructor
public class TourController {

    private final ITourService service;

    @GetMapping
    private ResponseEntity<List<Tour>> findAll() throws Exception {
        List<Tour> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Tour> findById(@PathVariable Integer id) throws Exception {
        Tour obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Tour> save(@RequestBody Tour tour) throws Exception {
        Tour obj = service.save(tour);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Tour> update(@PathVariable Integer id, @RequestBody Tour tour) throws Exception {
        Tour obj = service.update(id, tour);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



