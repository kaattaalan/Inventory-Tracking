package com.rm.inventorytracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.domain.Purchase;

@Controller
public class PurchaseController {

	@RequestMapping("/purchase/add")
	public ModelAndView getAddpurchasePage() {
		return new ModelAndView("addpurchase", "purchase", new Purchase());
	}
}
