package com.dh.ssiservice.controller;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;
    private ArrayList<Category> categoryList;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        categoryList = new ArrayList<>();
        categoryList.add(new Category());
        when(categoryService.findAll()).thenReturn(categoryList);
        when(categoryService.findByCode(any())).thenReturn(categoryList);
    }

    @Test
    public void testGetCategoriesById() throws Exception {
        when(categoryService.findById(anyLong())).thenReturn(categoryList.get(0));

        Response response = categoryController.getCategoriesById(1L);
        assertEquals(categoryList.get(0), response.getEntity());
    }
}