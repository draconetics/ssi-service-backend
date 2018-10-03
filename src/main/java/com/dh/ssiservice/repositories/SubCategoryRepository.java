package com.dh.ssiservice.repositories;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.model.SubCategory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
    Optional<List<Category>> findByCode(String code);
    
    @Query("SELECT s FROM SubCategory s WHERE s.category.id = :catId")
    List<SubCategory> getSubCategoriesByCategory(Long catId);
    
    
}
