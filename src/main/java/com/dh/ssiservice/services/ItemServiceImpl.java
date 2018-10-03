/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.dao.ItemCommand;
import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item> implements ItemService {
    private static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    private ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected CrudRepository<Item, Long> getRepository() {
        return repository;
    }

    @Override
    public void saveImage(Long id, InputStream file) {
        Item itemPersisted = findById(id);
        try {
            Byte[] bytes = ImageUtils.inputStreamToByteArray(file);
            itemPersisted.setImage(bytes);
            getRepository().save(itemPersisted);
        } catch (IOException e) {
            logger.error("Error reading file", e);
            e.printStackTrace();
        }
    }
    
    @Override
    public Page<Item> findItemsRandom(Pageable pageable){
    	return repository.findItemsRandom(pageable);
    }

//    @Override
//    public Page<ItemCommand> getRandomItemsPageable(Pageable pageable){
//
//        List<ItemCommand> items = new ArrayList<>();
//       // items = repository.findAll();
//        Page<ItemCommand> paging= new PageImpl<>(items, pageable, items.size());
//
//        return paging;
//    }
    
    @Override
    public Page<Item>findItemBySubCategory(Pageable pageable, Long id){
    	return repository.findItemBySubCategory(pageable, id);
    }
    
    @Override
    public Page<Item> findItemByCategory(Pageable pageable, Long id){
    	return repository.findItemByCategory(pageable, id);
    }
    
    @Override
    public Page<Item> findItemByString(Pageable pageable, String search){
    	return repository.findItemByString(pageable, search);
    }
}