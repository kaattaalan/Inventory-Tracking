package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final RoomService roomService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,RoomService roomService) {
        this.itemRepository = itemRepository;
        this.roomService = roomService;

    }
    public void addItem(ItemAddForm form) {
        for (int i = 0; i < form.getAmount(); i++) {
            String inventoryCode = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(10); //generate random string
            Item item = new Item(inventoryCode, form.getItemType());
            itemRepository.save(item);

            System.out.println(itemRepository.findOne(item.getId()));
        }
    }
    @Override
    public Iterable<Item> getItems() {
        return itemRepository.findAll();
    }
    @Override
    public void deleteItemById(long id) {
        itemRepository.delete(id);
    }


    public Item getItemById(long id) {
        return itemRepository.findOne(id);
    }


    public Item assignItem(String roomName, long itemId) {
        Room room = roomService.getRoomByRoomName(roomName);
        Item item = getItemById(itemId);
        Set<Item> itemList = room.getItems();
        itemList.add(item);
        room.setItems(itemList);

        item.setRoom(room);
        return itemRepository.save(item);
    }


}
