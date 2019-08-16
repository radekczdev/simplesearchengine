package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.czajor.simplesearchengine.SimpleSearchEngine.DOCUMENT_REPOSITORY;

public class InitiateDatabase {
    DocumentRepository repository = DocumentRepository.getInstance();

    public void init(String path) {
        try {
            repository.addDocuments(readFromFile(path));
        } catch (Exception e) {
            System.out.println("Initializing database thrown error: " + e.getMessage());
        }
    }

    public List<Document> readFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        return mapper.readValue(file, new TypeReference<List<Document>>(){});
    }

}
