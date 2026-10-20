package com.example.tourapp.controller;

import com.example.tourapp.dto.TourDTO;
import com.example.tourapp.model.Tour;
import com.example.tourapp.service.ITourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/tours")
@RequiredArgsConstructor
public class TourController {

    private final ITourService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<TourDTO>> findAll() throws Exception {
        List<TourDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TourDTO> findById(@PathVariable Integer id) throws Exception {
        Tour obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody TourDTO dto) throws Exception {
        Tour obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdTour())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<TourDTO> update(@PathVariable Integer id, @Valid @RequestBody TourDTO dto) throws Exception {
        Tour obj = convertToEntity(dto);
        obj.setIdTour(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Tour convertToEntity(TourDTO dto) {
        return modelMapper.map(dto, Tour.class);
    }

    private TourDTO convertToDTO(Tour obj) {
        return modelMapper.map(obj, TourDTO.class);
    }
}
