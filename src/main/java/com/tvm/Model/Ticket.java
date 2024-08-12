package com.tvm.Model;

import com.tvm.constants.TicketType;

import java.time.LocalDateTime;

public class Ticket {

    private static int entityCount = 0;

    private final long id;
    private final long userId;
    private TicketType ticketType;
    private final LocalDateTime creationDate;

    public Ticket(LocalDateTime creationDate, TicketType ticketType, long userId, long id) {
        this.creationDate = creationDate;
        this.ticketType = ticketType;
        this.userId = userId;
        this.id = id;
    }

    public Ticket(TicketType ticketType, long userId){
        this.ticketType = ticketType;
        this.userId = userId;
        this.id = ++entityCount;
        this.creationDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
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
