package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Driver;
import com.example.tourapp.repository.IDriverRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService extends GenericService<Driver, Integer> implements IDriverService {

    private final IDriverRepository repo;

    @Override
    protected IGenericRepository<Driver, Integer> getRepo() {
        return repo;
    }
}
