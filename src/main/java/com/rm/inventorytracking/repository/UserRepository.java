package com.rm.inventorytracking.repository;

import com.rm.inventorytracking.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
