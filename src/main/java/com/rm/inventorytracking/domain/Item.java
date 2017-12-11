package com.rm.inventorytracking.domain;

import javax.persistence.*;
//oluşturduğumuz database için entity olacak
@Entity
public class Item {

    @Id
    //primary key olan id'yi otomatik olarak oluşturuyoruz
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false ,updatable = false)
    private long id;

    @Column(name = "code" ,nullable = false ,updatable = false ,unique = true)
    private String inventoryCode;

    @Column(name = "type" ,nullable = false)
    private String type;

    public Item(){

    }

    public Item(String inventoryCode, String type){
        this.inventoryCode = inventoryCode;

        this.type = type;
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
