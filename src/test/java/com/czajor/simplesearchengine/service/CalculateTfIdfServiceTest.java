package com.czajor.simplesearchengine.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CalculateTfIdfServiceTest {
    private CalculateTfIdfService service = new CalculateTfIdfService();

    @Test
    public void shouldCalculateTf() {
        double tf = 3.0/7.0;
        double rfCalculated = service.calculateTf(3, 7);
        assertEquals(tf, rfCalculated, 0.001);
    }

    @Test
    public void shouldCalculateIdf() {
        double idf = Math.log10(2.0);
        double idfCalculated = service.calculateIdf(2, 1);
        assertEquals(idf, idfCalculated, 0.001);
    }

    @Test
    public void shouldCalculateTfIdf() {
        double tf = 3.0/7.0;
        double idf = Math.log10(2.0);
        double tfIdf = tf * idf;
        double tfIdfCalculated = service.calculateTfIdf(tf, idf);
        assertEquals(tfIdf, tfIdfCalculated, 0.001);
    }
}