package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;

public interface ItemService {

    void addItem(ItemAddForm form);


    Iterable<Item> getItems();

    //id'yi parametre alarak item silmek
    void deleteItemById(long id);


    // userın item listesine ilgili itemın eklenmesi ve bunun yanında, o itemın userı olarakta
    // select boxdan seçtiğimiz userın set edilmesi için bu tanımlamaları yapıyoruz
    Item getItemById(long id);
    Item assignItem(String username, long itemId);
}
