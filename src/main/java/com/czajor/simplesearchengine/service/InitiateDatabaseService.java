package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.SimpleSearchEngine;
import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
public class InitiateDatabaseService {
    private final Set<Document> documents = new HashSet<>();

    public void addToDatabase(String path, DocumentRepository repository) throws FileNotFoundException{
        try {
            documents.addAll(readFromFile(path));
            repository.addDocuments(documents);
        } catch (Exception e) {
            throw new FileNotFoundException("Initializing database thrown error: " + e.getMessage());
        }
    }

    private List<Document> readFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = SimpleSearchEngine.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        return mapper.readValue(file, new TypeReference<List<Document>>(){});
    }

}
