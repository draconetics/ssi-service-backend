/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item> implements ItemService {
    private static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

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
}