package com.rm.inventorytracking.controller;

import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }


    @RequestMapping("/add_room")
    public ModelAndView getAddRoomPage(){

        return new ModelAndView("addroom","room", new Room());
    }

    @RequestMapping(value = "/add_room", method = RequestMethod.POST)
    public String handleAddRoomForm(@Valid @ModelAttribute("room") Room room, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return "add_room";

        roomService.addRoom(room);
        return "redirect:/";

    }

    @RequestMapping("/rooms")
    public ModelAndView getRoomsPage(){
        return new ModelAndView("rooms","rooms",roomService.getRooms());
    }


}
