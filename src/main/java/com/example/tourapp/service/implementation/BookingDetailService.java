package com.example.tourapp.service.implementation;

import com.example.tourapp.model.BookingDetail;
import com.example.tourapp.repository.IBookingDetailRepository;
import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IBookingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingDetailService extends GenericService<BookingDetail, Integer> implements IBookingDetailService {

    private final IBookingDetailRepository repo;

    @Override
    protected IGenericRepository<BookingDetail, Integer> getRepo() {
        return repo;
    }
}
