package com.rm.inventorytracking.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.domain.ItemAssignForm;
import com.rm.inventorytracking.service.ItemService;
import com.rm.inventorytracking.service.RoomService;

@Controller
public class ItemController {
	private final ItemService itemService;
	private final RoomService roomService;

	@Autowired
	public ItemController(ItemService itemService, RoomService roomService) {
		this.itemService = itemService;
		this.roomService = roomService;

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/items/add")
	public ModelAndView itemAddPage() {
		return new ModelAndView("addItem", "itemForm", new ItemAddForm());
	}

	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "addItem";
		itemService.addItem(form);
		return "redirect:/items";
	}

	// To send more than one model, we created a <String, Object> map, and added
	// our model to this map.
	@RequestMapping("/items")
	public ModelAndView getItemsPage() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("items", itemService.getItems());
		model.put("roomNames", roomService.getRoomNames());
		model.put("assignForm", new ItemAssignForm());
		return new ModelAndView("items", model);
	}

	// because we do update, we send a PUT request.
	// The parameters of the assignItem method are the room id from the DTO and
	// the item id which is PathVariable.
	// After the process is completed, we are forwarding the user to the items
	// page.
	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
	public String handleItemAssign(@ModelAttribute("room") ItemAssignForm form, @PathVariable("id") long id) {
		itemService.assignItem(form.getRoomName(), id);
		return "redirect:/items";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
	public String handleItemDelete(@PathVariable Long id) {
		itemService.deleteItemById(id);
		return "redirect:/items";
	}

}