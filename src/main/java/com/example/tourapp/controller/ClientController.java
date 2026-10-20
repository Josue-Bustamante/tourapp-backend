package com.example.tourapp.controller;

import com.example.tourapp.dto.ClientDTO;
import com.example.tourapp.model.Client;
import com.example.tourapp.service.IClientService;
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
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<ClientDTO>> findAll() throws Exception {
        List<ClientDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClientDTO> findById(@PathVariable Integer id) throws Exception {
        Client obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody ClientDTO dto) throws Exception {
        Client obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdClient())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO dto) throws Exception {
        Client obj = convertToEntity(dto);
        obj.setIdClient(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Client convertToEntity(ClientDTO dto) {
        return modelMapper.map(dto, Client.class);
    }

    private ClientDTO convertToDTO(Client obj) {
        return modelMapper.map(obj, ClientDTO.class);
    }
}
