package com.rm.inventorytracking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rm.inventorytracking.domain.RawMaterial;
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
		return new ModelAndView("rawmaterials", "rawMaterialList", rawMaterialService.getRawMaterialList());
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/rawmaterials/add")
	public ModelAndView getAddRawMaterialPage() {
		return new ModelAndView("addrawmaterials", "rawMaterial", new RawMaterial());
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/rawmaterials/add", method = RequestMethod.POST)
	public String addRawMaterial(@Valid @ModelAttribute("rawMaterial") RawMaterial rawMaterial,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors() || rawMaterialService.existsByType(rawMaterial.getType())) {
			return "redirect:/rawmaterials/add";
		}
		rawMaterialService.addRawMaterial(rawMaterial);
		return "redirect:/rawmaterials";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/rawmaterials/{id}", method = RequestMethod.DELETE)
	public String deleteRawMaterial(@PathVariable Long id) {
		if (null != rawMaterialService.getRawMaterialById(id)) {
			rawMaterialService.deleteRawMaterial(id);
		}
		return "redirect:/rawmaterials";
	}

}
