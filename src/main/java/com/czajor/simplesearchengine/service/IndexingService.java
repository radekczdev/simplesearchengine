package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@EqualsAndHashCode
public class IndexingService {

    public void run(DocumentRepository documentRepository, IndexRepository indexRepository) {
        Set<Document> documents = documentRepository.getDocuments();
        documents.forEach(doc -> {
                    Set<String> indexes = new HashSet<>(doc.getIndexMap().keySet());
                    for (String curIndex : indexes) {
                        Optional<Index> wrappedIndex = indexRepository.findIndexById(curIndex);
                        if (wrappedIndex.isPresent()) {
                            Index index = wrappedIndex.get();
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
