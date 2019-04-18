package com.rm.inventorytracking.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.rm.inventorytracking.domain.PurchaseDetails;

public class PurchaseForm {

	private Long purchaseId;
	private Date purchaseDate;
	private String remarks;

	private List<PurchaseDetails> purchaseDetails = new ArrayList<PurchaseDetails>();
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PurchaseDetails> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public void incrementPurchaseDetails(){
		this.purchaseDetails.add(new PurchaseDetails());
	}

}
