package com.rm.inventorytracking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.domain.PurchaseForm;
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
		return new ModelAndView("purchases");
	}

	@RequestMapping("/purchase/add")
	public ModelAndView getAddpurchasePage() {
		ModelAndView mView = new ModelAndView("addpurchase");
		mView.addObject("purchase", new PurchaseForm());
		mView.addObject("items", rawMaterialService.getRawMaterialList());
		return mView;
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute("itemForm") PurchaseForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addPurchase";
		}
		purchaseService.addPurchase(form);
		return "redirect:/purchase/add";
	}
}
