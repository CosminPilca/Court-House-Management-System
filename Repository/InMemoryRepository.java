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
        String id = idExtractor.apply(entity);
        if (storage.containsKey(id)) {
            throw new IllegalArgumentException("Entity with ID " + id + " already exists.");
        }
        storage.put(id, entity);
    }

    @Override
    public T read(String id) {
        return storage.get(id);
    }

    @Override
    public void update(T entity) {
        String id = idExtractor.apply(entity);
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException("Entity with ID " + id + " does not exist.");
        }
        storage.put(id, entity);
    }

    @Override
    public void delete(String id) {
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException("Entity with ID " + id + " does not exist.");
        }
        storage.remove(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storage.values());    }
}