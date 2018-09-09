package com.dh.ssiservice.model;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CategoryTest {

    private static final String EXPECTED_NAME = "edson";

    @Test
    public void testGetName() {
        Category category = new Category();
        category.setName(EXPECTED_NAME);
        assertEquals(category.getName(), EXPECTED_NAME);
    }
}