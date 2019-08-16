package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexingServiceTest {
    private IndexingService service = new IndexingService();
    private IndexRepository INDEX_REPOSITORY = IndexRepository.getInstance();

    @Mock
    DocumentRepository DOCUMENT_REPOSITORY;

    @Test
    public void shouldExistInTwoDocuments() {
        // Given
        Document document1 = new Document("the brown fox jumped over the brown dog");
        Document document2 = new Document("the lazy brown dog sat in the corner");
        Document document3 = new Document("the red fox bit the lazy dog behind the corner");
        String searchedPhrase = "corner";
        Set<Document> documents = new HashSet<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
        when(DOCUMENT_REPOSITORY.getDocuments()).thenReturn(documents);
        service.run(DOCUMENT_REPOSITORY, INDEX_REPOSITORY);

        // When
        int noOfDocuments = INDEX_REPOSITORY.findIndexById(searchedPhrase).get().getDocumentsContaining().size();

        // Then
        verify(DOCUMENT_REPOSITORY).getDocuments();
        assertEquals(2, noOfDocuments);
    }

}