package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

/**
 * model objesini almak, yaratmak, güncellemek
 */

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }
    public Item getItemByCode(String code) {
        return itemRepository.findByInventoryCode(code);
    }

    public void addItem(ItemAddForm form) { //itemin type'ı stoğa kaç tane ekleneceği bilgisini alıyoruz

        for (int i = 0; i < form.getAmount(); i++) {

            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string

            Item item = new Item(inventoryCode, form.getItemType());

            itemRepository.save(item);
            System.out.println(itemRepository.findOne(item.getId()));
        }
    }

    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.delete(id); //CRUD repository fonksiyonlarından delete() kullanarak item id'sini parametre olarak gönderip item'ı sildik.
    }

    public Item getItemById(long id) {
        return itemRepository.findOne(id);
    }
    // // ItemAssignForm‘dan gelecek olan kullanıcı adına sahip userın item listesine, itemId‘ye sahip Item‘ı ekliyoruz

    public Item assignItem(String username, long itemId) {
        User user = userService.getUserByUsername(username);
        Item item = getItemById(itemId);
        //bu itemın userını, getUserByUsername methoduyla aldığımız user’a set ediyoruz
        Set<Item> itemList = user.getItems();
        itemList.add(item);
        user.setItems(itemList);
        //database'e ekliyoruz.
        item.setUser(user);
        return itemRepository.save(item);
    }
}
