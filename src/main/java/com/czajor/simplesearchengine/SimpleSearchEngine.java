package com.czajor.simplesearchengine;

import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;

public class SimpleSearchEngine {
    public final IndexRepository indexRepository = IndexRepository.getInstance();
    public final DocumentRepository documentRepository = DocumentRepository.getInstance();

    public static void main(String[] args) {

    }
}
