package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.Room;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override //user ekleme
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //userları çekiyoruz.

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


    public List<String> getUsernames() {
        List<String> usennames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()){
            User user = (User) iterator.next();
            usennames.add(user.getUsername());
        }
        return usennames;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Map<String, List<Room>> numberOfRoomsByType(long userId) {
        Map<String,List<Room>> map = new HashMap<String, List<Room>>();
        Set<Room> rooms = getUserById(userId).getRooms();

        for(Room room: rooms){
            List<Room> roomList = new ArrayList<Room>();
            String key = room.getRoomName().toLowerCase();

            if(map.containsKey(key))
                roomList = map.get(key);

            roomList.add(room);
            map.put(key,roomList);
        }
        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }
    }


}