package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Trip;
import com.example.tourapp.repository.ITripRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.ITripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService extends GenericService<Trip, Integer> implements ITripService {

    private final ITripRepository repo;

    @Override
    protected IGenericRepository<Trip, Integer> getRepo() {
        return repo;
    }
}
