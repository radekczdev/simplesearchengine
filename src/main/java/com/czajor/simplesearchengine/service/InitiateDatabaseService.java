package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
public class InitiateDatabaseService {
    private final Set<Document> documents = new HashSet<>();

    public void addToDatabase(String path, DocumentRepository repository) {
        try {
            documents.addAll(readFromFile(path));
            repository.addDocuments(documents);
        } catch (Exception e) {
            System.out.println("Initializing database thrown error: " + e.getMessage());
        }
    }

    public List<Document> readFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        return mapper.readValue(file, new TypeReference<List<Document>>(){});
    }

}
