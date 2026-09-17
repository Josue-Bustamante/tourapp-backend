package com.example.tourapp.controller;

import com.example.tourapp.model.Place;
import com.example.tourapp.service.IPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final IPlaceService service;

    @GetMapping
    private ResponseEntity<List<Place>> findAll() throws Exception {
        List<Place> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Place> findById(@PathVariable Integer id) throws Exception {
        Place obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Place> save(@RequestBody Place place) throws Exception {
        Place obj = service.save(place);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Place> update(@PathVariable Integer id, @RequestBody Place place) throws Exception {
        Place obj = service.update(id, place);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


