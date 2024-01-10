package com.example.ecommerce.service;

import java.util.List;

public interface IService<E> {
    List<E> findAll();

    void save(E e);

    void edit(E e);

    void delete(int id);

    E findByTeamId(int id);
}
