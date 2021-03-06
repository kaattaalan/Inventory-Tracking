package com.rm.inventorytracking.domain;

import javax.persistence.*;
//oluşturduğumuz database için entity olacak
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "code", nullable = false, updatable = false, unique = true)
    private String inventoryCode;

    @Column(name = "type", nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "date", nullable = false)
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Item(){

    }

    public Item(String inventoryCode, String type, String date){
        this.inventoryCode = inventoryCode;
        this.date = date;
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", inventoryCode='" + inventoryCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
