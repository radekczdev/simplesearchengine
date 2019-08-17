package com.czajor.simplesearchengine.service;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CalculateTfIdfService {
    public double calculateTf(int wordOccurance, int allWords) {
        return (double) wordOccurance / (double) allWords;
    }

    public double calculateIdf(int allDocuments, int docsContainingWord) {
        return Math.log10((double) allDocuments / (double) docsContainingWord);
    }

    public double calculateTfIdf(double tf, double idf) {
        return tf * idf;
    }
}
