package com.courthouse.repository;

import java.util.*;
import java.util.function.Function;

public class InMemoryRepository<T> implements IRepository<T> {
    private final Map<String, T> storage = new HashMap<>();
    private final Function<T, String> idExtractor;

    public InMemoryRepository(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void create(T entity) {
        // TO DO
    }

    @Override
    public T read(String id) {
        // TO DO
    }

    @Override
    public void update(T entity) {
        // TO DO
    }

    @Override
    public void delete(String id) {
        // TO DO
    }

    @Override
    public List<T> getAll() {
        // TO DO
    }
}