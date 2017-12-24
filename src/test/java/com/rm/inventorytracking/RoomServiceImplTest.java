package com.rm.inventorytracking;

import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.repository.RoomRepository;
import com.rm.inventorytracking.service.RoomService;
import com.rm.inventorytracking.service.RoomServiceImpl;
import com.rm.inventorytracking.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
/**
 * Room testi
 * oluşturulan oda sisteme kaydediliyor mu
 * ona bakıldı
 *
 *
 */
@RunWith(SpringRunner.class)
public class RoomServiceImplTest {

   @TestConfiguration
   static class RoomServiceImplTestContextConfiguration{

       @Autowired
       private RoomRepository roomRepository;

       @Autowired
       private UserService userService;
       @Bean
       RoomService roomService(){
           return new RoomServiceImpl(roomRepository,userService);
       }

   }

   @Autowired
    private RoomService roomService;

   @MockBean
    private RoomRepository roomRepository;

   @Before
    public void setUp(){
       Room room = new Room("room_1");
       roomRepository.save(room);

       Mockito.when(roomRepository.findByRoomName(room.getRoomName()))
               .thenReturn(room);
   }

   @Test
    public void Valid(){
        String name = "room_1";
        Room found = roomService.getRoomByRoomName(name);

        assertThat(found.getRoomName())
                .isEqualTo(name);

   }

}
