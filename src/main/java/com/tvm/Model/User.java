package com.tvm.Model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private LocalDateTime creationDate;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", name='" + name + '\'' +
                '}';
    }

    public User() {}

    public User(int id, LocalDateTime creationDate, String name) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
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
