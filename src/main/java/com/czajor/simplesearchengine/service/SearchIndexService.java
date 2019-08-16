package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

public class SearchIndexService {
    private CalculateTfIdfService tfIdfService = new CalculateTfIdfService();

    private Index find(String indexId, IndexRepository repository) {
        return repository.findIndexById(indexId).orElseThrow(() -> new IllegalArgumentException("Index doesn't exist in any document."));
    }

    public void sortByTfIdf(String indexId, IndexRepository indexRepository, DocumentRepository documentRepository) {
        Index index = find(indexId, indexRepository);
        Set<Long> documentsContainingIndex = index.getDocumentsContaining();
        Map<Long, Integer> wordOccurrenceInDocuments = new HashMap<>();
        Map<Long, Double> tfCalculatedForDocuments = new HashMap<>();
        Map<Long, Double> tfIdfCalculatedForDocuments = new HashMap<>();
        Map<Long, Double> tfIdfCalculatedForDocumentsSorted = new HashMap<>();

        double idf = tfIdfService.calculateIdf(documentRepository.getDocuments().size(), index.getDocumentsContaining().size());

        documentsContainingIndex.stream()
                .forEach(a -> {
                        int occurrenceInDoc = documentRepository
                                .findDocumentById(a)
                                .get().getIndexMap().get(indexId);

                        // TODO : add condition for what if doc doesn't have this index
                        wordOccurrenceInDocuments.put(a, occurrenceInDoc);
                    }
                );

        wordOccurrenceInDocuments.entrySet().stream().forEach(
                doc ->
                        tfCalculatedForDocuments.put(
                                doc.getKey(),
                                tfIdfService.calculateTf(
                                        doc.getValue(),
                                        documentRepository.findDocumentById(doc.getKey()).get().getIndexMap().size()
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

        tfIdfCalculatedForDocumentsSorted = tfIdfCalculatedForDocuments.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));

        System.out.println("Documents sorted using TF-IDF statistic:" + tfIdfCalculatedForDocumentsSorted.toString());

    }

    public void print(Index index) {
        System.out.println("Index " + index.getValue() + " , found in documents: ");
        index.getDocumentsContaining().forEach(System.out::println);
    }
}
