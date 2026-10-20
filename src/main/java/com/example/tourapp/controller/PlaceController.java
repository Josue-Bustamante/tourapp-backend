package com.example.tourapp.controller;

import com.example.tourapp.dto.PlaceDTO;
import com.example.tourapp.model.Place;
import com.example.tourapp.service.IPlaceService;
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
@RequestMapping("/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final IPlaceService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<PlaceDTO>> findAll() throws Exception {
        List<PlaceDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PlaceDTO> findById(@PathVariable Integer id) throws Exception {
        Place obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody PlaceDTO dto) throws Exception {
        Place obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdPlace())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<PlaceDTO> update(@PathVariable Integer id, @Valid @RequestBody PlaceDTO dto) throws Exception {
        Place obj = convertToEntity(dto);
        obj.setIdPlace(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Place convertToEntity(PlaceDTO dto) {
        return modelMapper.map(dto, Place.class);
    }

    private PlaceDTO convertToDTO(Place obj) {
        return modelMapper.map(obj, PlaceDTO.class);
    }
}
