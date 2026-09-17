package com.example.tourapp.controller;

import com.example.tourapp.model.Bus;
import com.example.tourapp.service.IBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/buses")
@RequiredArgsConstructor
public class BusController {

    private final IBusService service;

    @GetMapping
    private ResponseEntity<List<Bus>> findAll() throws Exception {
        List<Bus> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Bus> findById(@PathVariable Integer id) throws Exception {
        Bus obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Bus> save(@RequestBody Bus bus) throws Exception {
        Bus obj = service.save(bus);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Bus> update(@PathVariable Integer id, @RequestBody Bus bus) throws Exception {
        Bus obj = service.update(id, bus);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

