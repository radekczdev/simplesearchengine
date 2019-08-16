package com.czajor.simplesearchengine.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class IndexTestSuite {

    @Test
    public void shouldExistInThreeDocuments() {
        // Given
        Index index = new Index("fox");
        index.addDocument(0L);
        index.addDocument(5L);
        index.addDocument(4L);

        // When
        int documentsContainingNumber = index.getDocumentsContaining().size();
        //Then
        assertEquals(3, documentsContainingNumber);
    }

}