package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;

public interface ItemService {

    void addItem(ItemAddForm form);

    Iterable<Item> getItems();
    void deleteItemById(long id);

    Item getItemById( long id);
    Item assignItem(String roomName, long itemId);

}
