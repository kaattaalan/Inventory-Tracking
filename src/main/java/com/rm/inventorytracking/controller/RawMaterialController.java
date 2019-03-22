package com.rm.inventorytracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.service.RawMaterialService;

@Controller
public class RawMaterialController {

	private RawMaterialService rawMaterialService;

	@Autowired
	public RawMaterialController(RawMaterialService rawMaterialService) {
		this.rawMaterialService = rawMaterialService;
	}

	@RequestMapping("/rawmaterials")
	public ModelAndView getRawMaterialsPage() {
		return new ModelAndView("rawMaterials", "rawMaterialList", rawMaterialService.getRawMaterialList());
	}

}
