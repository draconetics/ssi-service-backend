/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Category;

import java.util.List;


public interface CategoryService extends GenericService<Category> {
    List<Category> findByCode(String code);
}
