package com.rm.inventorytracking.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PurchaseForm {

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

	public void incrementPurchaseDetails(){
		this.purchaseDetails.add(new PurchaseDetails());
	}

}
