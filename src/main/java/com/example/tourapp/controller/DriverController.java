package com.example.tourapp.controller;

import com.example.tourapp.model.Driver;
import com.example.tourapp.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final IDriverService service;

    @GetMapping
    private ResponseEntity<List<Driver>> findAll() throws Exception {
        List<Driver> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Driver> findById(@PathVariable Integer id) throws Exception {
        Driver obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Driver> save(@RequestBody Driver driver) throws Exception {
        Driver obj = service.save(driver);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Driver> update(@PathVariable Integer id, @RequestBody Driver driver) throws Exception {
        Driver obj = service.update(id, driver);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


