package com.example.tourapp.controller;

import com.example.tourapp.model.UserData;
import com.example.tourapp.service.IUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/userdata")
@RequiredArgsConstructor
public class UserDataController {

    private final IUserDataService service;

    @GetMapping
    private ResponseEntity<List<UserData>> findAll() throws Exception {
        List<UserData> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserData> findById(@PathVariable Integer id) throws Exception {
        UserData obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<UserData> save(@RequestBody UserData userdata) throws Exception {
        UserData obj = service.save(userdata);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserData> update(@PathVariable Integer id, @RequestBody UserData userdata) throws Exception {
        UserData obj = service.update(id, userdata);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



