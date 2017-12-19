package com.rm.inventorytracking.controller;

import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.ItemAddForm;
import com.rm.inventorytracking.domain.ItemAssignForm;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.service.ItemService;
import com.rm.inventorytracking.service.RoomService;
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
    private final RoomService roomService;

    @Autowired
    public ItemController(ItemService itemService,RoomService roomService) {
        this.itemService = itemService;
        this.roomService = roomService;

    }

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
    //birden fazla model göndermek için bir <String, Object> mapi oluşturup, modellerimizi bu mape ekledik.
    @RequestMapping("/items")
    public ModelAndView getItemsPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("items",itemService.getItems());
        model.put("roomNames",roomService.getRoomNames());
        model.put("assignForm",new ItemAssignForm());
        return new ModelAndView("items",model);
    }

    //update işlemi yaptığımız için, PUT isteği yolluyoruz.
    // assignItem methodunun parametreleri DTO’dan gelen room ve PathVariable olan item id‘sini kullanıyoruz.
    // İşlem tamamlandıktan sonra kullanıcıyı items sayfasına yönlendiriyoruz.
    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public String handleItemAssign(@ModelAttribute("room") ItemAssignForm form, @PathVariable("id") long id){
        itemService.assignItem(form.getRoomName(),id);
        return "redirect:/items";
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

}