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
    @Size(min = 3, max = 20)
    @Column(name = "name_room", nullable = false, unique = true)
    private String name_room;

    @OneToMany(mappedBy = "room")
    private Set<Item> items;


    public Room(){}

    public Room(String name_room){
        this.name_room = name_room;
    }

    public Long getId() {
        return id;
    }

    public String getName_room() {
        return name_room;
    }
    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }


}
