package com.czajor.simplesearchengine;

import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;

public class SimpleSearchEngine {
    private static final IndexRepository indexRepository = IndexRepository.getInstance();
    private static final DocumentRepository documentRepository = DocumentRepository.getInstance();

    public static void main(String[] args) {
        Runner runner = new Runner(indexRepository, documentRepository);
        try {
            runner.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
