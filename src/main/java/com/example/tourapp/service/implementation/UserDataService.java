package com.example.tourapp.service.implementation;

import com.example.tourapp.model.UserData;
import com.example.tourapp.repository.IUserDataRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataService extends GenericService<UserData, Integer> implements IUserDataService {

    private final IUserDataRepository repo;

    @Override
    protected IGenericRepository<UserData, Integer> getRepo() {
        return repo;
    }
}
