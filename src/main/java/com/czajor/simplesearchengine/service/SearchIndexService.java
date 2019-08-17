package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class SearchIndexService {
    private IndexRepository indexRepository;
    private DocumentRepository documentRepository;
    private CalculateTfIdfService tfIdfService = new CalculateTfIdfService();

    public SearchIndexService(IndexRepository indexRepository, DocumentRepository documentRepository) {
        this.indexRepository = indexRepository;
        this.documentRepository = documentRepository;
    }

    private Index find(String indexId) {
        return indexRepository.findIndexById(indexId).orElseThrow(() -> new IllegalArgumentException("Index doesn't exist in any document."));
    }

    public Map<Long, Double> sortByTfIdf(String indexId) {
        Index index = find(indexId);
        double idf = tfIdfService.calculateIdf(documentRepository.getDocuments().size(), index.getDocumentsContaining().size());
        Set<Long> documentsContainingIndex = index.getDocumentsContaining();
        Map<Long, Integer> wordOccurrenceInDocuments = findIndexOccurrenceInDocuments(indexId, documentsContainingIndex);
        Map<Long, Double> tfCalculatedForDocuments = calculateTfForDocuments(wordOccurrenceInDocuments);
        Map<Long, Double> tfIdfCalculatedForDocuments = calculateTfIdfForDocuments(tfCalculatedForDocuments, idf);
        return sortByTfIdf(tfIdfCalculatedForDocuments);
    }

    private Map<Long, Integer> findIndexOccurrenceInDocuments(String indexId, Set<Long> docs) {
        return docs.stream()
                .collect(Collectors.toMap(Long::longValue, a -> documentRepository
                        .findDocumentById(a)
                        .get()
                        .getIndexMap()
                        .get(indexId)));
    }

    private Map<Long, Double> calculateTfForDocuments(Map<Long, Integer> wordOccurrenceInDocuments) {
        Map<Long, Double> tfCalculatedForDocuments = new HashMap<>();
        wordOccurrenceInDocuments.forEach(
                (key, value) ->
                        tfCalculatedForDocuments.put(
                                key,
                                tfIdfService.calculateTf(value, documentRepository.findDocumentById(key).get().getSize())
                        )
        );
        return tfCalculatedForDocuments;
    }

    private Map<Long, Double> calculateTfIdfForDocuments(Map<Long, Double> tfCalculatedForDocuments,
                                                         double idf) {
        return tfCalculatedForDocuments.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        value -> tfIdfService.calculateTfIdf(value.getValue(), idf)
        ));
    }

    private Map<Long, Double> sortByTfIdf(Map<Long, Double> tfIdfCalculatedForDocuments) {
        return tfIdfCalculatedForDocuments.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));
    }
}
