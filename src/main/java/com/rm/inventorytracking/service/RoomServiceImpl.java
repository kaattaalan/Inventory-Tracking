package com.rm.inventorytracking.service;

import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }


    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }
}
