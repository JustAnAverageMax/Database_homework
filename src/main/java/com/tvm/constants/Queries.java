package com.tvm.constants;

public class Queries {
    public static final String DELETE_USER_BY_ID = "DELETE FROM \"user\" WHERE id = ?";
    public static final String SAVE_USER = "INSERT INTO \"user\" (name) VALUES (?)";
    public static final String GET_USER_BY_ID = "SELECT * FROM  \"user\" WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE \"user\" SET name = ? WHERE id = ?";

    public static final String GET_TICKETS_BY_USER_ID = "SELECT * FROM  ticket WHERE user_id = ?";
    public static final String DELETE_TICKET_BY_USER_ID = "DELETE FROM ticket WHERE user_id = ?";
    public static final String SAVE_TICKET = "INSERT INTO ticket (ticket_type, user_id) VALUES (?::ticket_type, ?)";
    public static final String GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE id = ?";
    public static final String UPDATE_TICKET_TYPE = "UPDATE ticket SET ticket_type = ? WHERE id = ?";
    public static final String DELETE_TICKET_BY_ID = "DELETE FROM ticket WHERE id = ?";
}
