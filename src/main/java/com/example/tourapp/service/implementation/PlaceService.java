package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Place;
import com.example.tourapp.repository.IPlaceRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService extends GenericService<Place, Integer> implements IPlaceService {

    private final IPlaceRepository repo;

    @Override
    protected IGenericRepository<Place, Integer> getRepo() {
        return repo;
    }
}
