package com.rm.inventorytracking.controller;

import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.service.ItemService;
import com.rm.inventorytracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ItemController {

    private final ItemService itemService;


    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;


    }

    @RequestMapping("/items/add")
    public ModelAndView itemAddPage() {

        return new ModelAndView("addItem", "itemForm", new ItemAddForm());
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult) { //item ekleme
                                //valid ve bindind result'ı form validationı için kullanıyoruz.
                                //sorun yoksa item'ı database'e ekliyoruz
        if (bindingResult.hasErrors())
            return "addItem";

        itemService.addItem(form);
        return "redirect:/items";
    }

    @RequestMapping("/items")
    public ModelAndView getItemPage() {
        //items view'ına yönlendirme
        return new ModelAndView("items","items",itemService.getItems());
    }

    //item/{id} silindikten sonra /items URL'ine yönlendirme yap
    @RequestMapping(value = "/items/{id}",method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }
}

