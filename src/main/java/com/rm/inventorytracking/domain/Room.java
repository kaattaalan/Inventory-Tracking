package com.rm.inventorytracking.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "roomName", nullable = false, unique = true)
    private String roomName;

    @OneToMany(mappedBy = "room")
    private Set<Item> items;

    public Room(){

    }
    public Room(String roomName){
        this.roomName = roomName;
    }

    public long getId() {
        return id;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString(){
        return roomName;
    }


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
