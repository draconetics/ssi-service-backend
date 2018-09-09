package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.repositories.CategoryRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    private static final String OTRA_CAT = "OTRACAT";

    private List<Category> categorySet;
    private Category category = new Category();
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categorySet = new ArrayList<>();
        category.setName(OTRA_CAT);
        categorySet.add(category);
        when(categoryRepository.findAll()).thenReturn(categorySet);
    }

    @Test
    public void testGetCategories() {
        List<Category> result = categoryServiceImpl.findAll();
//        verify(categoryRepository,times(1)).findAll();
        verify(categoryRepository).findAll();
        assertEquals(result, categorySet);
        assertEquals(result.get(0).getName(), OTRA_CAT);
    }

    @Test
    public void testFindByCode() {
        when(categoryRepository.findByCode(any())).thenReturn(Optional.of(categorySet));

        List<Category> result = categoryServiceImpl.findByCode("code");
        assertEquals(result, Collections.singletonList(category));
    }

    @Test
    public void testFindById() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        Category result = categoryServiceImpl.findById(1L);
        assertEquals(result, category);
    }
}