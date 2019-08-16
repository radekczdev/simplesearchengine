package com.czajor.simplesearchengine.repository;

import com.czajor.simplesearchengine.domain.Index;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class IndexRepository {
    private volatile static IndexRepository instance;
    private Set<Index> indexSet = new HashSet<>();

    private IndexRepository() {
    }

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
        if (!indexSet.contains(index)) {
            indexSet.add(index);
        } else {
            throw new IllegalArgumentException("Index already exists");
        }
    }

    public Optional<Index> findIndexById(String id) {
        return indexSet.stream()
                .filter(a -> a.equals(new Index(id)))
                .findAny();
    }


}
