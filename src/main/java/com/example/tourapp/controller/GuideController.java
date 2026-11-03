package com.example.tourapp.controller;

import com.example.tourapp.dto.GuideDTO;
import com.example.tourapp.model.Guide;
import com.example.tourapp.service.IGuideService;
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
@RequestMapping("/v1/guides")
@RequiredArgsConstructor
public class GuideController {

    private final IGuideService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<GuideDTO>> findAll() throws Exception {
        List<GuideDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuideDTO> findById(@PathVariable Integer id) throws Exception {
        Guide obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody GuideDTO dto) throws Exception {
        Guide obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdGuide())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuideDTO> update(@PathVariable Integer id, @Valid @RequestBody GuideDTO dto) throws Exception {
        Guide obj = convertToEntity(dto);
        obj.setIdGuide(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Guide convertToEntity(GuideDTO dto) {
        return modelMapper.map(dto, Guide.class);
    }

    private GuideDTO convertToDTO(Guide obj) {
        return modelMapper.map(obj, GuideDTO.class);
    }
}



