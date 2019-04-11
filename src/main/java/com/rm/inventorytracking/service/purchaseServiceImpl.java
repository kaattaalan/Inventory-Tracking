package com.rm.inventorytracking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.inventorytracking.domain.Purchase;
import com.rm.inventorytracking.domain.PurchaseDetails;
import com.rm.inventorytracking.domain.PurchaseForm;
import com.rm.inventorytracking.repository.PurchaseDetailsRepository;
import com.rm.inventorytracking.repository.PurchaseRepository;

@Service
public class purchaseServiceImpl implements PurchaseService {
	PurchaseRepository purchaseRepo;
	PurchaseDetailsRepository purchaseDetailsrepo;

	@Autowired
	public purchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseDetailsRepository purchaseDetailsrepo) {
		this.purchaseRepo = purchaseRepository;
		this.purchaseDetailsrepo = purchaseDetailsrepo;
	}

	@Override
	public void addPurchase(PurchaseForm form) {
		Purchase purchase = new Purchase();
		purchase.setDate(form.getPurchaseDate());
		purchase.setRemarks(form.getRemarks());

		PurchaseDetails purchaseDetails = new PurchaseDetails();
		purchaseDetails.setItemId(form.getItemId());
		purchaseDetails.setItemQuantity(form.getItemCount());
		purchase.addPurchaseDetails(purchaseDetails);
		purchaseRepo.save(purchase);
		purchaseDetailsrepo.save(purchaseDetails);
	}

	@Override
	public List<Purchase> getAllPurchases() {
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		Iterable<Purchase> iterable = purchaseRepo.findAll();
		for (Purchase purchase : iterable) {
			purchaseList.add(purchase);
		}
		return purchaseList;
	}

}
