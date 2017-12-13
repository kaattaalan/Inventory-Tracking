package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Room;

public interface RoomService {
    Room addRoom(Room room);

    Iterable<Room> getRooms();

}
