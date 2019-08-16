package com.czajor.simplesearchengine.domain;

import com.czajor.simplesearchengine.domain.Document;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DocumentTestSuite {

    @Test
    public void shouldReturnNumberOfBrownIndex () {
        // Given
        String key = "brown";
        int valueExpected = 2;
        String documentText = "the lazy brown dog sat in the corner by the brown fox";
        Document document = new Document(documentText);
        Map<String, Integer> indexMap = document.getIndexMap();

        // When
        int currentValue = indexMap.get(key);

        // Then
        assertEquals(valueExpected, currentValue);
    }

}
