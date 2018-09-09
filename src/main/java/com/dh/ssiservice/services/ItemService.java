/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.services;

import com.dh.ssiservice.model.Item;

import java.io.InputStream;

public interface ItemService extends GenericService<Item> {
    void saveImage(Long id, InputStream file);
}