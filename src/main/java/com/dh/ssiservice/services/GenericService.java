/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import java.util.List;


public interface GenericService<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T model);

    void deleteById(Long id);
}
