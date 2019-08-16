package com.czajor.simplesearchengine.repository;

import com.czajor.simplesearchengine.domain.Index;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class IndexRepositoryTest {
    private Index testIndex1;
    private Index testIndex2;
    private IndexRepository repository;

    @Before
    public void setUp() throws Exception {
        testIndex1 = new Index("brown");
        testIndex2 = new Index("blue");
        repository = IndexRepository.getInstance();
        repository.addIndex(testIndex1);
        repository.addIndex(testIndex2);
    }

    @Test
    public void shouldFindTwoIndexes() {
        // Given
        List<Index> indexes = new LinkedList<>();
        List<String> ids = new ArrayList<>();
        ids.add("brown");
        ids.add("blue");
        ids.add("red");
        ids.forEach(s -> repository
                .findIndexById(s)
                .ifPresent(indexes::add)
        );

        // When
        int indexesFound = indexes.size();

        // Then
        assertEquals(2, indexesFound);
    }
}