package com.example.tourapp.service.implementation;

import com.example.tourapp.repository.IGenericRepository;
import com.example.tourapp.service.IGenericService;

import java.util.List;

public abstract class GenericService<T, ID> implements IGenericService<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new Exception("No such id : " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
