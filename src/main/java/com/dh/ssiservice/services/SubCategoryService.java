/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import java.util.List;

import com.dh.ssiservice.model.SubCategory;

public interface SubCategoryService extends GenericService<SubCategory> {
	
	List<SubCategory> getSubCategoriesByCategory(Long catId);
}