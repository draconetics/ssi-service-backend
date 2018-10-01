package com.dh.ssiservice.repositories;

import com.dh.ssiservice.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query(value="SELECT * FROM item ORDER BY RAND() ", nativeQuery = true)
    Page<Object[]> findItemsRandom(Pageable pageable);
}
