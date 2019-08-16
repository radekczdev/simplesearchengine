package com.czajor.gi.repository;

import com.czajor.simplesearchengine.domain.Index;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class IndexRepository {
    private volatile static IndexRepository instance;
    private final Set<Index> indexSet = new HashSet<>();

    public static IndexRepository getInstance() {
        if(instance == null) {
            synchronized (IndexRepository.class) {
                if (instance == null) {
                    instance = new IndexRepository();
                }
            }
        }
        return instance;
    }
    public void addIndex(final Index index) {
        indexSet.add(index);
    }

    public Optional<Index> findIndexById(String id) {
        return indexSet.stream()
                .filter(a -> a.equals(new Index(id)))
                .findAny();
    }

    private IndexRepository() {
    }
}
