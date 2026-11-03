package com.example.tourapp.controller;

import com.example.tourapp.dto.BusDTO;
import com.example.tourapp.model.Bus;
import com.example.tourapp.service.IBusService;
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
@RequestMapping("/v1/buses")
@RequiredArgsConstructor
public class BusController {

    private final IBusService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BusDTO>> findAll() throws Exception {
        List<BusDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> findById(@PathVariable Integer id) throws Exception {
        Bus obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody BusDTO dto) throws Exception {
        Bus obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdBus())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> update(@PathVariable Integer id, @Valid @RequestBody BusDTO dto) throws Exception {
        Bus obj = convertToEntity(dto);
        obj.setIdBus(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Bus convertToEntity(BusDTO dto) {
        return modelMapper.map(dto, Bus.class);
    }

    private BusDTO convertToDTO(Bus obj) {
        return modelMapper.map(obj, BusDTO.class);
    }
}

