package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Client;
import com.example.tourapp.repository.IClientRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService extends GenericService<Client, Integer> implements IClientService {

    private final IClientRepository repo;

    @Override
    protected IGenericRepository<Client, Integer> getRepo() {
        return repo;
    }
}
