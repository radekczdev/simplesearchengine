package com.czajor.simplesearchengine.service;

import com.czajor.simplesearchengine.domain.Index;

public class PrintToConsoleService {
    public static void searchResult(Index index) {
        System.out.println("Documents found for index: \"" + index.getValue() + "\",  sorted using TF-IDF statistic:" + index.getTfIdfMap());
    }

    public static void printWelcome() {
        System.out.println("*** Welcome to the search index ***");
        System.out.println("Application provides search of documents containing requested Index.");
        System.out.println("Input should be placed in resources folder in .json file. One json element contains _body_ field with the String.");
        System.out.println("Please provide path to the file with documents:");
    }

    public static void printProvideIndex() {
        System.out.println("Provide a word to be searched: ");
    }
}
