package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final UserService userService;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, UserService userService) {
        this.roomRepository = roomRepository;
        this.userService = userService;
    }


    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }


    public List<String> getRoomNames(){
        List<String> roomNames = new ArrayList<String>();
        Iterator iterator = getRooms().iterator();

        while (iterator.hasNext()){
            Room room = (Room) iterator.next();
            roomNames.add(room.getRoomName());
        }

        return roomNames;
    }

    public Room getRoomByRoomName(String roomName){
        return roomRepository.findByRoomName(roomName);
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findOne(id);
    }

    @Override
    public Map<String, List<Item>> numberOfItemsByType(long roomId) {
        Map<String, List<Item>> map = new HashMap<String, List<Item>>();
        Set<Item> items = getRoomById(roomId).getItems();

        for (Item item : items){
            List<Item> itemList = new ArrayList<Item>();
            String key = item.getType().toLowerCase();

            if (map.containsKey(key))
                itemList = map.get(key);

            itemList.add(item);
            map.put(key, itemList);
        }
        return map;
    }

    @Override
    public Room assignRoom(String username, long roomId) {
        User user = userService.getUserByUsername(username);
        Room room = getRoomById(roomId);

        Set<Room> roomList = user.getRooms();
        roomList.add(room);
        user.setRooms(roomList);

        room.setUser(user);

        return roomRepository.save(room);
    }
}
