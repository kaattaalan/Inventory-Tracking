package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;

public interface ItemService {

    void addItem(ItemAddForm form);


    Iterable<Item> getItems();

    //id'yi parametre alarak item silmek
    void deleteItemById(long id);
}
