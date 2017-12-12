package com.rm.inventorytracking.domain;


import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 20)
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user") // User ve Item aralarında one-to-many ilişkisi olan birer Entity.
                                  // User‘ın itemlarını bu Set içerisinde belirtiyoruz
    private Set<Item> items;
    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}