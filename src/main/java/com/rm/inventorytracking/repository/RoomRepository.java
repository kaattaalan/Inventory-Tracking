package com.rm.inventorytracking.repository;

import com.rm.inventorytracking.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
