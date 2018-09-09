package com.dh.ssiservice.repositories;

import com.dh.ssiservice.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
