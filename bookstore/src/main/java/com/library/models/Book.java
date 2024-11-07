package com.library.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Book {

    private int id;
    private String name;
    private String author;
    private float price;
    @JsonManagedReference
    private Set<User> users;

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

}
