package com.example.tourapp.controller;

import com.example.tourapp.dto.BookingDTO;
import com.example.tourapp.model.Booking;
import com.example.tourapp.service.IBookingService;
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
@RequestMapping("/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<BookingDTO>> findAll() throws Exception {
        List<BookingDTO> list = service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<BookingDTO> findById(@PathVariable Integer id) throws Exception {
        Booking obj = service.findById(id);
        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody BookingDTO dto) throws Exception {
        Booking obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdBooking())
                .toUri();
        return ResponseEntity.created(location).build(); // 201
    }

    @PutMapping("/{id}")
    private ResponseEntity<BookingDTO> update(@PathVariable Integer id, @Valid @RequestBody BookingDTO dto) throws Exception {
        Booking obj = convertToEntity(dto);
        obj.setIdBooking(id);
        return ResponseEntity.ok(convertToDTO(service.update(id, obj))); // 200
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    private Booking convertToEntity(BookingDTO dto) {
        return modelMapper.map(dto, Booking.class);
    }

    private BookingDTO convertToDTO(Booking obj) {
        return modelMapper.map(obj, BookingDTO.class);
    }
}
