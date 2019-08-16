package com.czajor.simplesearchengine.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
public class Index {
    private final String value;
    private final Set<Long> documentsContaining = new HashSet<>();
//    private Set<Document, TF>
//    private IDF;

    public Index(String value) {
        this.value = value;
    }

    public void addDocument(Long id) {
        if (!documentsContaining.contains(id)) {
            documentsContaining.add(id);
        } else {
            throw new IllegalArgumentException("Index has this documents id");
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
