package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import lombok.EqualsAndHashCode;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@EqualsAndHashCode
public class SearchIndexService {
    private CalculateTfIdfService tfIdfService = new CalculateTfIdfService();
    private Map<Long, Double> tfIdfCalculatedForDocumentsSorted = new HashMap<>();

    private Index find(String indexId, IndexRepository repository) {
        return repository.findIndexById(indexId).orElseThrow(() -> new IllegalArgumentException("Index doesn't exist in any document."));
    }

    public Map<Long, Double> sortByTfIdf(String indexId, IndexRepository indexRepository, DocumentRepository documentRepository) {
        Index index = find(indexId, indexRepository);
        Set<Long> documentsContainingIndex = index.getDocumentsContaining();
        Map<Long, Integer> wordOccurrenceInDocuments = new HashMap<>();
        Map<Long, Double> tfCalculatedForDocuments = new HashMap<>();
        Map<Long, Double> tfIdfCalculatedForDocuments = new HashMap<>();

        double idf = tfIdfService.calculateIdf(documentRepository.getDocuments().size(), index.getDocumentsContaining().size());

        documentsContainingIndex.stream()
                .forEach(a ->
                        // TODO: Check if indexId exists
                        documentRepository
                                .findDocumentById(a)
                                .get()
                                .getIndexMap()
                                .computeIfPresent(indexId, (key, val) -> wordOccurrenceInDocuments.put(a, val))
                );

        wordOccurrenceInDocuments.entrySet().stream().forEach(
                doc ->
                        tfCalculatedForDocuments.put(
                                doc.getKey(),
                                tfIdfService.calculateTf(
                                        doc.getValue(),
                                        documentRepository.findDocumentById(doc.getKey()).get().getSize()
                                )
                        )
        );

        wordOccurrenceInDocuments.entrySet().stream().forEach(
                doc ->
                        tfIdfCalculatedForDocuments.put(
                                doc.getKey(),
                                tfIdfService.calculateTfIdf(
                                        tfCalculatedForDocuments.get(doc.getKey()),
                                        idf
                                )
                        )
        );

        tfIdfCalculatedForDocumentsSorted = tfIdfCalculatedForDocuments.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));

        return tfIdfCalculatedForDocumentsSorted;
    }

    public void print(String indexId) {
        System.out.println("Documents found for index: \"" + indexId + "\",  sorted using TF-IDF statistic:" + tfIdfCalculatedForDocumentsSorted.toString());
    }
}
