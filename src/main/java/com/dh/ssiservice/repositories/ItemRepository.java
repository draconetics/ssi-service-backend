package com.dh.ssiservice.repositories;

import com.dh.ssiservice.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query(value="SELECT i FROM Item i ORDER BY RAND()")
    Page<Item> findItemsRandom(Pageable paging);
    
    //SELECT * FROM item, sub_category WHERE item.sub_category_id=sub_category.id and sub_category.id =4
    @Query(value="SELECT i FROM Item i, SubCategory s WHERE i.subCategory.id=s.id and s.id =:idSubCat")
    Page<Item> findItemBySubCategory(Pageable paging, Long idSubCat);
    
    @Query(value="SELECT i FROM Item i, SubCategory s, Category c WHERE i.subCategory.id=s.id and s.category.id =c.id and c.id=:idCat")
    Page<Item> findItemByCategory(Pageable paging, Long idCat);
    
    @Query(value="SELECT i FROM Item i WHERE i.name LIKE %:x%  OR i.code LIKE %:x% ")
    Page<Item> findItemByString(Pageable pageable, String x);
    
//	@Query("select i form Item")
//	Page<Item> findByName( Pageable pageable);
}
