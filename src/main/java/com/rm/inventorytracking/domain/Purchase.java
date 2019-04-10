package com.rm.inventorytracking.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "remarks")
	private String Remarks;

	@OneToMany(mappedBy = "purchase")
	private List<PurchaseDetails> purchaseDetails = new ArrayList<PurchaseDetails>();

	public void addPurchaseDetails(PurchaseDetails details) {
		this.purchaseDetails.add(details);
		details.setPurchase(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public List<PurchaseDetails> getPurchasedetails() {
		return purchaseDetails;
	}

	public void setPurchasedetails(List<PurchaseDetails> purchasedetails) {
		this.purchaseDetails = purchasedetails;
	}

}
