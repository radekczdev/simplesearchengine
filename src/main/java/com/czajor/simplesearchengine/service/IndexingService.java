package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.czajor.simplesearchengine.SimpleSearchEngine.DOCUMENT_REPOSITORY;
import static com.czajor.simplesearchengine.SimpleSearchEngine.INDEX_REPOSITORY;

public class IndexingService {
    public void run() {
        Set<Document> documents = DOCUMENT_REPOSITORY.getDocuments();
        documents.stream()
                .forEach(doc -> {
                    Set<String> indexes = doc.getIndexMap().entrySet().stream()
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet());
                    for (String curIndex : indexes) {
                        Optional<Index> indexOpt = INDEX_REPOSITORY.findIndexById(curIndex);
                        if (indexOpt.isPresent()) {
                            Index index = indexOpt.get();
                            index.addDocument(doc.getId());
                        } else {
                            Index index = new Index(curIndex);
                            INDEX_REPOSITORY.addIndex(index);
                        }
                    }
                });
    }
}
