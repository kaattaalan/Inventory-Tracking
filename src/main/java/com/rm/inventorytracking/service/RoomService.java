package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.Room;

import java.util.List;
import java.util.Map;

public interface RoomService  {
    Room addRoom(Room room);

    Iterable<Room> getRooms();

    List<String> getRoomNames();
    Room getRoomByRoomName(String roomName);

    Room getRoomById(long id);
    Map<String, List<Item>> numberOfItemsByType(long roomId);

    Room assignRoom(String username, long roomId);

    void deleteRoomById(long id);


}