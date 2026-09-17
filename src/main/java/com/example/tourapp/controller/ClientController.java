package com.example.tourapp.controller;

import com.example.tourapp.model.Client;
import com.example.tourapp.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;

    @GetMapping
    private ResponseEntity<List<Client>> findAll() throws Exception {
        List<Client> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Client> findById(@PathVariable Integer id) throws Exception {
        Client obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Client> save(@RequestBody Client client) throws Exception {
        Client obj = service.save(client);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody Client client) throws Exception {
        Client obj = service.update(id, client);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
