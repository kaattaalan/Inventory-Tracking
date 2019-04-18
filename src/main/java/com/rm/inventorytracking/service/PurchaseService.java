package com.rm.inventorytracking.service;

import java.util.List;

import com.rm.inventorytracking.dao.PurchaseForm;
import com.rm.inventorytracking.domain.Purchase;

public interface PurchaseService {

	void addPurchase(PurchaseForm form);
	
	List<Purchase> getAllPurchases();
	
	Purchase getPurchaseById(Long id);
	
	void deletepurchase(Purchase purchase);

	void deletepurchaseById(Long id);
	
}
