package com.czajor.simplesearchengine.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Set;

@Getter
@ToString
public class Index {
    private final String value;
    private Set<Document> documentsContaining;
//    private Set<Document, TF>
//    private IDF;

    public Index(String value) {
        this.value = value;
    }

    public void addDocument(Document document) {
        documentsContaining.add(document);
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
