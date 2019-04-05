package com.rm.inventorytracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rm.inventorytracking.domain.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
