package com.rm.inventorytracking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.dao.PurchaseForm;
import com.rm.inventorytracking.service.PurchaseService;
import com.rm.inventorytracking.service.RawMaterialService;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	private RawMaterialService rawMaterialService;

	public PurchaseController(PurchaseService purchaseService, RawMaterialService rawmaterialService) {
		this.purchaseService = purchaseService;
		this.rawMaterialService = rawmaterialService;
	}

	@RequestMapping("/purchase")
	public ModelAndView getPurchasesPage() {
		return new ModelAndView("purchases", "purchases", purchaseService.getAllPurchases());
	}

	@RequestMapping("/purchase/add")
	public ModelAndView getAddpurchasePage() {
		ModelAndView mView = new ModelAndView("addpurchase");
		mView.addObject("purchase", new PurchaseForm());
		mView.addObject("items", rawMaterialService.getRawMaterialList());
		return mView;
	}

	@RequestMapping(value = "/purchase/details", method = RequestMethod.POST)
	public ModelAndView getAddPurchaseDetailsPage(@Valid @ModelAttribute("itemForm") PurchaseForm form,
			BindingResult bindingResult) {
		ModelAndView mView = new ModelAndView("addpurchasedetails");
		mView.addObject("purchase", form);
		mView.addObject("items", rawMaterialService.getRawMaterialList());
		return mView;
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST, params = "action=save")
	public String savePurchase(@Valid @ModelAttribute("purchase") PurchaseForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addPurchase";
		}
		if(form.getPurchaseId() == null){
			purchaseService.addPurchase(form);
		}else{
			purchaseService.updatePurchase(form);
		}
		return "redirect:/purchase";
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST, params = "action=add")
	public ModelAndView addRowToPurchase(@Valid @ModelAttribute("purchase") PurchaseForm form,
			BindingResult bindingResult) {
		ModelAndView mView = new ModelAndView("addpurchasedetails");
		form.incrementPurchaseDetails();
		mView.addObject("purchase", form);
		mView.addObject("items", rawMaterialService.getRawMaterialList());
		return mView;
	}

	@RequestMapping(value = "/purchase/{id}", method = RequestMethod.DELETE)
	public String deletePurchase(@PathVariable Long id) {
		if (null != purchaseService.getPurchaseById(id)) {
			purchaseService.deletepurchaseById(id);
		}
		return "redirect:/purchase";
	}

	@RequestMapping(value = "/purchase/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewPurchase(@PathVariable Long id) {
		PurchaseForm form = purchaseService.createPurchaseFormById(id);
		ModelAndView mView = new ModelAndView("addpurchasedetails");
		mView.addObject("purchase", form);
		mView.addObject("items", rawMaterialService.getRawMaterialList());
		return mView;
	}

}
