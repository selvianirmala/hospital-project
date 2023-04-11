package org.example.repository;

import org.example.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    T create(T params) throws Exception;

    List<T> getAll() throws Exception;

    Optional<T> findById(int id) throws Exception;

    void update(T params, int id) throws Exception;

    void delete(int id) throws Exception;
}
