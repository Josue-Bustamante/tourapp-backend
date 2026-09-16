package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Tour;
import com.example.tourapp.repository.ITourRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.ITourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService extends GenericService<Tour, Integer> implements ITourService {

    private final ITourRepository repo;

    @Override
    protected IGenericRepository<Tour, Integer> getRepo() {
        return repo;
    }
}
