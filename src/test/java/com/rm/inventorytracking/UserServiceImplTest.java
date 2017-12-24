package com.rm.inventorytracking;

import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.UserRepository;
import com.rm.inventorytracking.service.UserService;
import com.rm.inventorytracking.service.UserServiceImpl;

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
 * user testi
 * oluşturulan kullanıcı sisteme kaydediliyor mu
 * ona bakıldı
 *
 *
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {


    @TestConfiguration
    static class UserServiceImplTestContextConfiguration{

        @Autowired
        private UserRepository userRepository;
        @Bean
        UserService userService(){
            return new UserServiceImpl(userRepository);
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private  UserRepository userRepository;
    @Before
    public void setUp(){
        User user1 = new User("mehmet", "123456");
        user1.setName("Mehmet");
        user1.setLastName("Koca");
        userRepository.save(user1);

        Mockito.when(userRepository.findByUsername(user1.getUsername()))
                .thenReturn(user1);

    }

    @Test
    public void Valid(){
        String name = "mehmet";
        User found = userService.getUserByUsername(name);

        assertThat(found.getUsername())
                .isEqualTo(name);
    }
}
