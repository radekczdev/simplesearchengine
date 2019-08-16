package com.czajor.simplesearchengine;

import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;

public class SimpleSearchEngine {
    public final IndexRepository INDEX_REPOSITORY = IndexRepository.getInstance();
    public final DocumentRepository DOCUMENT_REPOSITORY = DocumentRepository.getInstance();

    public static void main(String[] args) {

    }
}
