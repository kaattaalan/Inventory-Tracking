package com.rm.inventorytracking.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *  ItemAddForm‘un görevi ihtiyacımız olan datayı View katmanından alıp Service katmanına taşımak.
 *  Bu taşıma işini yapan objelere DTO (Data Transfer Object) deniyor. DTO ile Model katmanında kullandığımız
 *  ana objelerin arasındaki fark, DTO’ların datayı alma ve datayı kaydetme harici (set ve get methodları)
 *  herhangi bir sorumluluğu olmaması, yani üzerlerinde başka method tanımlanmamasıdır.
 */



public class ItemAddForm {
    @NotEmpty
    @Size(min=2, max=50)
    private String itemType;
    @NotNull
    private int amount = 1; //default
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}