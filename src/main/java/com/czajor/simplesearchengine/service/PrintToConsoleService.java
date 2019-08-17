package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Index;

public class PrintToConsoleService {
    public static void searchResult(Index index) {
        System.out.println("Documents found for index: \"" + index.getValue() + "\",  sorted using TF-IDF statistic:" + index.getTfIdfMap());
    }
}
