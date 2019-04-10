package com.rm.inventorytracking.service;

import java.util.List;

import com.rm.inventorytracking.domain.Purchase;
import com.rm.inventorytracking.domain.PurchaseForm;

public interface PurchaseService {

	void addPurchase(PurchaseForm form);
	
	List<Purchase> getAllPurchases();
	
}
