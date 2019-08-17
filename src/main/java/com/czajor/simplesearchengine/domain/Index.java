package com.czajor.simplesearchengine.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class Index {
    private final String value;
    private final Set<Long> documentsContaining = new HashSet<>();
    private Map<Long, Double> tfIdfMap = new HashMap<>();

    public Index(String value) {
        this.value = value;
    }

    public void addDocument(Long id) {
        if (!documentsContaining.contains(id)) {
            documentsContaining.add(id);
        } else {
            throw new IllegalArgumentException("Index already contains this document id");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return Objects.equals(value, index.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
