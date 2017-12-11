package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * model objesini almak, yaratmak, güncellemek
 */

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;


    }
    public void addItem(ItemAddForm form) { //itemin type'ı stoğa kaç tane ekleneceği bilgisini alıyoruz

        for (int i = 0; i < form.getAmount(); i++) {

            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string

            Item item = new Item(inventoryCode, form.getItemType());

            itemRepository.save(item);
            System.out.println(itemRepository.findOne(item.getId()));
        }
    }

    @Override
    public Iterable<Item> getItems() {
        return itemRepository.findAll(); //CRUD repository fonksiyonlarından findAll() kullanarak bütün itemları döndürdük.
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.delete(id); //CRUD repository fonksiyonlarından delete() kullanarak item id'sini parametre olarak gönderip item'ı sildik.
    }
}
