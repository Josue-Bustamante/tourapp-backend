package com.example.tourapp.controller;

import com.example.tourapp.dto.DriverDTO;
import com.example.tourapp.model.Driver;
import com.example.tourapp.service.IDriverService;
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
@RequestMapping("/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final IDriverService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DriverDTO>> findAll() throws Exception {
        List<DriverDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Integer id) throws Exception {
        Driver obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DriverDTO dto) throws Exception {
        Driver obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdDriver())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(@PathVariable Integer id, @Valid @RequestBody DriverDTO dto) throws Exception {
        Driver obj = convertToEntity(dto);
        obj.setIdDriver(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Driver convertToEntity(DriverDTO dto) {
        return modelMapper.map(dto, Driver.class);
    }

    private DriverDTO convertToDTO(Driver obj) {
        return modelMapper.map(obj, DriverDTO.class);
    }
}


