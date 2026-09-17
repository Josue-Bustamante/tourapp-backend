package com.example.tourapp.controller;

import com.example.tourapp.model.Booking;
import com.example.tourapp.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService service;

    @GetMapping
    private ResponseEntity<List<Booking>> findAll() throws Exception {
        List<Booking> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Booking> findById(@PathVariable Integer id) throws Exception {
        Booking obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    private ResponseEntity<Booking> save(@RequestBody Booking booking) throws Exception {
        Booking obj = service.save(booking);
        return new ResponseEntity<>(obj, HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<Booking> update(@PathVariable Integer id, @RequestBody Booking booking) throws Exception {
        Booking obj = service.update(id, booking);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

