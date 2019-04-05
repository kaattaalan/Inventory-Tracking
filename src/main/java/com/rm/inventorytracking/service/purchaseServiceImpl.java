package com.rm.inventorytracking.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.inventorytracking.domain.Purchase;
import com.rm.inventorytracking.domain.PurchaseDetails;
import com.rm.inventorytracking.domain.PurchaseForm;
import com.rm.inventorytracking.repository.PurchaseRepository;

@Service
public class purchaseServiceImpl implements PurchaseService {
	PurchaseRepository purchaseRepo;

	@Autowired
	public purchaseServiceImpl(PurchaseRepository purchaseRepository) {
		this.purchaseRepo = purchaseRepository;
	}

	@Override
	public void addPurchase(PurchaseForm form) {
		Purchase purchase = new Purchase();
		purchase.setDate(form.getPurchaseDate());
		purchase.setRemarks(form.getRemarks());

		PurchaseDetails purchaseDetails = new PurchaseDetails();
		purchaseDetails.setItemId(form.getItemId());
		purchaseDetails.setItemQuantity(form.getItemCount());
		purchase.setPurchaseDetails(new HashSet<PurchaseDetails>() {
			{
				add(purchaseDetails);
			}
		});
		purchaseRepo.save(purchase);
	}

}
