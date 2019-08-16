package com.czajor.simplesearchengine.repository;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DocumentRepository {
    private  Set<Document> documents = new HashSet<>();
    private volatile static DocumentRepository instance;

    private DocumentRepository() {
    }

    public static DocumentRepository getInstance() {
        if(instance == null) {
            synchronized (DocumentRepository.class) {
                if (instance == null) {
                    instance = new DocumentRepository();
                }
            }
        }
        return instance;
    }
    
    public void addDocuments(Set<Document> document) {
        documents.addAll(document);
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public Set<Document> getDocuments() {
        return this.documents;
    }

    public Optional<Document> findDocumentById(Long id) {
        return documents.stream()
                .filter(a -> a.getId() == id)
                .findAny();
    }
}
