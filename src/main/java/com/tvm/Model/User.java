package com.tvm.Model;

import java.time.LocalDateTime;

public class User {
    private static long entityCount = 0;

    private final long id;
    private final LocalDateTime creationDate;
    private String name;


    public User(long id, LocalDateTime creationDate, String name) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
    }

    public User(String name){
        this.name = name;
        this.id = ++entityCount;
        this.creationDate = LocalDateTime.now();
    }

    public User() {
        this.id = ++entityCount;
        this.creationDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
