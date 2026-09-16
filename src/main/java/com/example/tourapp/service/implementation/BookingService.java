package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Booking;
import com.example.tourapp.repository.IBookingRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService extends GenericService<Booking, Integer> implements IBookingService {

    private final IBookingRepository repo;

    @Override
    protected IGenericRepository<Booking, Integer> getRepo() {
        return repo;
    }
}
