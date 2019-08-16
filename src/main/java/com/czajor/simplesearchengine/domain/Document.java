package com.czajor.simplesearchengine.domain;

import com.czajor.simplesearchengine.repository.IndexRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.czajor.simplesearchengine.SimpleSearchEngine.REPOSITORY;

@Getter
@EqualsAndHashCode
@ToString
public class Document {
    private static long idCounter = 0;
    private long id;
    private Map<Index, Integer> indexMap;

    public Document(final String body) {
        this.id = generateId();
//        indexMap = createIndexMap(body);
    }

    private static synchronized long generateId() {
        return idCounter++;
    }

//    private Map<Index, Integer> createIndexMap(String body) {
//        Map<String, Integer> indexMap = new HashMap<>();
//        Arrays.stream(body.split("\\s"))
//                .forEach(a -> {
//                    if(REPOSITORY.containsIndex(a)) {
//                        Index currentIndex = REPOSITORY.
//                        indexMap.replace(new Index(a), indexMap.get(a)+1);
//                    } else {
//                        indexMap.put(a,1);
//                    }
//                });
//        return index;
//    }
//
//    private int getToken
}
