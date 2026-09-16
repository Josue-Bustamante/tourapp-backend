package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Bus;
import com.example.tourapp.repository.IBusRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusService extends GenericService<Bus, Integer> implements IBusService {

    private final IBusRepository repo;

    @Override
    protected IGenericRepository<Bus, Integer> getRepo() {
        return repo;
    }
}
