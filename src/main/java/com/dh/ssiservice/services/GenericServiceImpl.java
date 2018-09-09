/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.exceptions.NotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

    @Override
    public List<T> findAll() {
        List<T> results = new ArrayList<>();
        getRepository().findAll().forEach(results::add);
        return results;
    }

    @Override
    public T findById(Long id) {
        Optional<T> optional = getRepository().findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException(getType() + " id:" + id + " Not Found");
        }
        return optional.get();
    }

    private String getType() {
        String typeName = (((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
        typeName = typeName.substring(typeName.lastIndexOf(".") + 1);
        return typeName;
    }

    @Override
    public T save(T model) {
        return getRepository().save(model);
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    protected abstract CrudRepository<T, Long> getRepository();
}
