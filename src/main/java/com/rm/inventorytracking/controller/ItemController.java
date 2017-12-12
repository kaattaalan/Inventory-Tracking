package com.rm.inventorytracking.controller;

import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.domain.ItemAssignForm;
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
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    //birden fazla model göndermek için bir <String, Object> mapi oluşturup, modellerimizi bu mape ekledik.
    @RequestMapping("/items")
    public ModelAndView getItemsPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("items", itemService.getItems());
        model.put("usernames", userService.getUsernames());
        model.put("assignForm", new ItemAssignForm());
        return new ModelAndView("items", model);
    }

    //Bupdate işlemi yaptığımız için, PUT isteği yolluyoruz.
    // assignItem methodunun parametreleri DTO’dan gelen usernamei ve PathVariable olan item id‘sini kullanıyoruz.
    // İşlem tamamlandıktan sonra kullanıcıyı items sayfasına yönlendiriyoruz.
    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public String handleItemAssign(@ModelAttribute("assignForm") ItemAssignForm form, @PathVariable("id") long id) {
        itemService.assignItem(form.getUsername(), id);
        return "redirect:/items";
    }
    @RequestMapping("/items/add")
    public ModelAndView itemAddPage() {
        return new ModelAndView("addItem", "itemForm", new ItemAddForm());
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String handleItemAdd(@Valid @ModelAttribute("itemForm") ItemAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) ////valid ve bindingresult'ı form validationı için kullanıyoruz.
            return "addItem";

        itemService.addItem(form);
        return "redirect:/items";
    }

    @RequestMapping(params="delete", value = "/items/{id}", method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }


}