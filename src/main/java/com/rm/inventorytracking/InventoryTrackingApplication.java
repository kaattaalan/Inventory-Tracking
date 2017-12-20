package com.rm.inventorytracking;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.ItemRepository;
import com.rm.inventorytracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class InventoryTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryTrackingApplication.class, args);
	}

}
