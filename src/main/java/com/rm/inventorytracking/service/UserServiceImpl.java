package com.rm.inventorytracking.service;


import com.rm.inventorytracking.domain.Item;
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
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
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

            /**
            * numberOfItemsByType methodunda <String, List<Item>> mapi oluşturuyoruz.
            * Bu mapte keyimiz itemın typeı, valuemuz ise o typetaki itemların bir listesi.
            * @param userId
            * @Return
            * @Author: Mehmet Koca
             */




            public Map<String, List<Item>> numberOfItemsByType(long userId) {
                Map<String, List<Item>> map = new HashMap<String, List<Item>>();
                Set<Item> items = getUserById(userId).getItems();

                for (Item item: items) {
                    List<Item> itemList = new ArrayList<Item>();
                    String key = item.getType().toLowerCase();

                    if (map.containsKey(key))
                        itemList = map.get(key);

                    itemList.add(item);
                    map.put(key, itemList);
                }

                return map;
            }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }

        return usernames;
    }

    /**
     * UserDetailsService
     *  Spring Security’nin user girişini
     *  loadUserByUsername methoduyla kontrol ettiği bir interface.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<SimpleGrantedAuthority> auth = (List<SimpleGrantedAuthority>) user.getAuthorities();

        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }
    }
}