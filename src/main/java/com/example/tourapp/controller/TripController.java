package com.example.tourapp.controller;

import com.example.tourapp.dto.TripDTO;
import com.example.tourapp.model.Trip;
import com.example.tourapp.service.ITripService;
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
@RequestMapping("/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final ITripService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<TripDTO>> findAll() throws Exception {
        List<TripDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TripDTO> findById(@PathVariable Integer id) throws Exception {
        Trip obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody TripDTO dto) throws Exception {
        Trip obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdTrip())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<TripDTO> update(@PathVariable Integer id, @Valid @RequestBody TripDTO dto) throws Exception {
        Trip obj = convertToEntity(dto);
        obj.setIdTrip(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Trip convertToEntity(TripDTO dto) {
        return modelMapper.map(dto, Trip.class);
    }

    private TripDTO convertToDTO(Trip obj) {
        return modelMapper.map(obj, TripDTO.class);
    }
}
