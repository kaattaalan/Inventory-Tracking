package com.rm.inventorytracking.controller;

import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.RoomAssignForm;
import com.rm.inventorytracking.domain.User;
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
import java.util.NoSuchElementException;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;
    User user;


    @Autowired
    public RoomController(RoomService roomService, UserService userService){
        this.userService = userService;

        this.roomService = roomService;
    }



    @RequestMapping("/rooms/add")
    public ModelAndView getAddRoomPage(){
        return new ModelAndView("addroom","room", new Room());

    }

    @RequestMapping(value = "/rooms/add", method = RequestMethod.POST)
    public String handleAddRoomForm(@Valid @ModelAttribute("room") Room room, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return "rooms/add";

        roomService.addRoom(room);
        return "redirect:/";

    }

    @RequestMapping("/rooms")
    public ModelAndView getRoomsPage(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("rooms",roomService.getRooms());
        model.put("usernames",userService.getUsernames());
        model.put("assignForm",new RoomAssignForm());


        return new ModelAndView("rooms",model);
    }

    @RequestMapping(value = "/rooms/{id}/items")
    public ModelAndView getRoomPage(@PathVariable Long id){
        if(null == roomService.getRoomById(id))
            throw new NoSuchElementException("Room with id : "+ id + "not found");
        else
            return new ModelAndView("roomItems","items",roomService.numberOfItemsByType(id));
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT)
    public String handleRoomAssign(@ModelAttribute("user") RoomAssignForm form, @PathVariable("id") long id) {
        roomService.assignRoom(form.getUsername(), id);
        return "redirect:/rooms";
    }





}
