package com.example.tourapp.service.implementation;

import com.example.tourapp.model.Guide;
import com.example.tourapp.repository.IGuideRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IGuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuideService extends GenericService<Guide, Integer> implements IGuideService {

    private final IGuideRepository repo;

    @Override
    protected IGenericRepository<Guide, Integer> getRepo() {
        return repo;
    }
}
