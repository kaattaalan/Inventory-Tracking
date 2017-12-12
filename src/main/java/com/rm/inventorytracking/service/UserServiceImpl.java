package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
import com.rm.inventorytracking.domain.User;
import com.rm.inventorytracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }



    public List<String> getUsernames() { //kullanıcı adlarının listesi
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }

        return usernames;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }


    /**
     * numberOfItemsByType methodunda <String, List<Item>> mapi oluşturuyoruz.
     * Bu mapte keyimiz itemın typeı, valuemuz ise o typetaki itemların bir listesi.
     * @param userId
     * @Return
     * @Author: Mehmet Koca
     */
    @Override
    public Map<String, List<Item>> numberOfItemsByType(long userId) {
        Map<String,List<Item>> map = new HashMap<String, List<Item>>();
        Set<Item> items = getUserById(userId).getItems();

        for (Item item: items) {
            List<Item> itemList = new ArrayList<Item>();
            String key = item.getType().toLowerCase();

            if (map.containsKey(key)) {
                itemList = map.get(key);
            }

            itemList.add(item);
            map.put(key,itemList);
        }

        return map;



    }
}