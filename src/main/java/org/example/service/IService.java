package org.example.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    T create(T params);

    List<T> getAll();

    Optional<T> findById(int id);

    void update(T params, int id);

    void delete(int id);
}
