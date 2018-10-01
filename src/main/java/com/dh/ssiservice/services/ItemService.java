/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.dao.ItemCommand;
import com.dh.ssiservice.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;

public interface ItemService extends GenericService<Item> {
    void saveImage(Long id, InputStream file);

    Page<ItemCommand> getRandomItemsPageable(Pageable pageable);
}