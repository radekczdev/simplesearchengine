package com.czajor.simplesearchengine;

import com.czajor.simplesearchengine.domain.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;
import java.util.stream.Stream;

@RunWith(JUnit4.class)
public class DocumentTestSuite {

    @Test
    public void shouldCreateIndex () {
        String documentText = "the lazy brown dog sat in the corner by the brown fox";
        Document document = new Document(documentText);
//        Map<String, Integer> index = document.getIndex();
//        index.forEach((a, b) -> System.out.println("Key: "+ a + " Value: " + b));
    }

}
