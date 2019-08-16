package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class IndexingService {

    public void run(DocumentRepository documentRepository, IndexRepository indexRepository) {
        Set<Document> documents = documentRepository.getDocuments();
        documents.forEach(doc -> {
                    Set<String> indexes = doc.getIndexMap().entrySet().stream()
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet());
                    for (String curIndex : indexes) {
                        Optional<Index> indexOpt = indexRepository.findIndexById(curIndex);
                        if (indexOpt.isPresent()) {
                            Index index = indexOpt.get();
                            index.addDocument(doc.getId());
                        } else {
                            Index index = new Index(curIndex);
                            index.addDocument(doc.getId());
                            indexRepository.addIndex(index);
                        }
                    }
                });
    }
}
