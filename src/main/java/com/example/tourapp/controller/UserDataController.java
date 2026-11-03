package com.example.tourapp.controller;

import com.example.tourapp.dto.UserDataDTO;
import com.example.tourapp.model.UserData;
import com.example.tourapp.service.IUserDataService;
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
@RequestMapping("/v1/userdata")
@RequiredArgsConstructor
public class UserDataController {

    private final IUserDataService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDataDTO>> findAll() throws Exception {
        List<UserDataDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDTO> findById(@PathVariable Integer id) throws Exception {
        UserData obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserDataDTO dto) throws Exception {
        UserData obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdUser())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDataDTO> update(@PathVariable Integer id, @Valid @RequestBody UserDataDTO dto) throws Exception {
        UserData obj = convertToEntity(dto);
        obj.setIdUser(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private UserData convertToEntity(UserDataDTO dto) {
        return modelMapper.map(dto, UserData.class);
    }

    private UserDataDTO convertToDTO(UserData obj) {
        return modelMapper.map(obj, UserDataDTO.class);
    }
}



