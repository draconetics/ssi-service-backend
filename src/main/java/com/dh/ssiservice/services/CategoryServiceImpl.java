/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.repositories.CategoryRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category> implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findByCode(String code) {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findByCode(code).get().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    protected CrudRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
