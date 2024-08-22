package com.tvm.Model;

import com.tvm.constants.TicketType;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int userId;
    private TicketType ticketType;
    private LocalDateTime creationDate;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                '}';
    }

    public Ticket() {}

    public Ticket(LocalDateTime creationDate, TicketType ticketType, int userId, int id) {
        this.creationDate = creationDate;
        this.ticketType = ticketType;
        this.userId = userId;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
