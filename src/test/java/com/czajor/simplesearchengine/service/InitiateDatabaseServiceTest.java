package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.repository.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InitiateDatabaseServiceTest {
    @Mock
    DocumentRepository documentRepository = mock(DocumentRepository.class);

    private String filePath = "documents_lines.json";

    @Test
    public void shouldReadThreeObjects() {
        InitiateDatabaseService initiateDatabaseService = new InitiateDatabaseService();
        initiateDatabaseService.addToDatabase(filePath, documentRepository);
        int sizeOfDatabase = initiateDatabaseService.getDocuments().size();

        assertEquals(3L, sizeOfDatabase);
    }

}