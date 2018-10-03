/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.SubCategory;
import com.dh.ssiservice.repositories.SubCategoryRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl extends GenericServiceImpl<SubCategory> implements SubCategoryService {
    private SubCategoryRepository repository;

    public SubCategoryServiceImpl(SubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected CrudRepository<SubCategory, Long> getRepository() {
        return repository;
    }
    
    @Override
    public List<SubCategory> getSubCategoriesByCategory(Long catId){
    	return repository.getSubCategoriesByCategory(catId);
    }
}