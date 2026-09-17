package com.example.tourapp.controller;

import com.example.tourapp.model.Guide;
import com.example.tourapp.service.IGuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/guides")
@RequiredArgsConstructor
public class GuideController {

    private final IGuideService service;

    @GetMapping
    private ResponseEntity<List<Guide>> findAll() throws Exception {
        List<Guide> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Guide> findById(@PathVariable Integer id) throws Exception {
        Guide obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Guide> save(@RequestBody Guide guide) throws Exception {
        Guide obj = service.save(guide);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Guide> update(@PathVariable Integer id, @RequestBody Guide guide) throws Exception {
        Guide obj = service.update(id, guide);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



