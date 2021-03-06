package com.czajor.simplesearchengine.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class Document {
    private static long idCounter = 0;
    @JsonIgnore
    private long id;
    private String body;
    @JsonIgnore
    private int size = 0;
    @JsonIgnore
    private Map<String, Integer> indexMap = new HashMap<>();

    @JsonCreator
    public Document(@JsonProperty("body") final String body) {
        this.body = body;
        this.id = generateId();
        createIndexMap(body);
    }

    private static synchronized long generateId() {
        return idCounter++;
    }

    private void createIndexMap(String body) {
        Arrays.stream(body.split("\\s"))
                .forEach(a -> {
                    size++;
                    if(indexMap.containsKey(a)) {
                        Integer keyValue = indexMap.get(a);
                        indexMap.replace(a, keyValue, keyValue+1);
                    } else {
                        indexMap.put(a, 1);
                    }
                        }
                );
    }
}
