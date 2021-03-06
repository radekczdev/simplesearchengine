package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchIndexServiceTest {
    private SearchIndexService service;
    @Mock
    IndexRepository indexRepository;

    @Mock
    DocumentRepository documentRepository;

    @Before
    public void setUp() throws Exception {
        service = new SearchIndexService(indexRepository, documentRepository);
    }

    @Test
    public void shouldFindTwoDocuments() {
        // Given
        Document document0L = new Document("the brown fox jumped over the brown dog");
        Document document10L = new Document("the lazy brown dog sat in the dog");
        Document document50L = new Document("the red fox bit the lazy dog fox");
        Set<Document> documents = new HashSet<>();
        documents.add(document0L);
        documents.add(document10L);
        documents.add(document50L);
        Optional<Document> wrappedDocument0L = Optional.of(document0L);
        Optional<Document> wrappedDocument50L = Optional.of(document50L);

        String indexId = "fox";
        Index index = new Index(indexId);
        index.addDocument(0L);
        index.addDocument(50L);

        Optional<Index> wrappedIndex = Optional.of(index);

        when(indexRepository.findIndexById(indexId)).thenReturn(wrappedIndex);
        when(documentRepository.getDocuments()).thenReturn(documents);
        when(documentRepository.findDocumentById(0L)).thenReturn(wrappedDocument0L);
        when(documentRepository.findDocumentById(50L)).thenReturn(wrappedDocument50L);

        // When
        Map<Long, Double> tfIdfMap = service.sortByTfIdf(indexId);
        index.getTfIdfMap().putAll(tfIdfMap);

        int amountOfDocsFound = index.getTfIdfMap().size();
        PrintToConsoleService.searchResult(index);

        // Then
        assertSame(2, amountOfDocsFound);
    }

    @Test
    public void shouldFindOneDocument() {
        // Given
        Document document0L = new Document("This is a a sample");
        Document document10L = new Document("This is another another example example example");
        Set<Document> documents = new HashSet<>();
        documents.add(document0L);
        documents.add(document10L);
        Optional<Document> wrappedDocument10L = Optional.of(document10L);

        String indexId = "example";
        Index index = new Index(indexId);
        index.addDocument(10L);
        Optional<Index> wrappedIndex = Optional.of(index);

        when(indexRepository.findIndexById(indexId)).thenReturn(wrappedIndex);
        when(documentRepository.getDocuments()).thenReturn(documents);
        when(documentRepository.findDocumentById(10L)).thenReturn(wrappedDocument10L);

        // When
        Map<Long, Double> tfIdfMap = service.sortByTfIdf(indexId);
        index.getTfIdfMap().putAll(tfIdfMap);

        int amountOfDocsFound = index.getTfIdfMap().size();
        PrintToConsoleService.searchResult(index);

        // Then
        assertEquals(1, amountOfDocsFound);
    }

}