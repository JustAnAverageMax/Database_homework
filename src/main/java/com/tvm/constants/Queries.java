package com.tvm.constants;

public class Queries {
    public static final String GET_ALL_USERS_IDS = "SELECT id FROM \"user\" ORDER BY id ASC";
    public static final String GET_ALL_USERS = "SELECT * FROM \"user\" ORDER BY id ASC";
    public static final String DELETE_USER_BY_ID = "DELETE FROM \"user\" WHERE id = ?";
    public static final String SAVE_USER = "INSERT INTO \"user\" (name) VALUES (?)";
    public static final String GET_USER_BY_ID = "SELECT * FROM \"user\" WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE \"user\" SET name = ? WHERE id = ?";

    public static final String GET_ALL_TICKETS_IDS = "SELECT id FROM ticket ORDER BY id ASC";
    public static final String GET_ALL_TICKETS = "SELECT * FROM ticket ORDER BY id ASC";
    public static final String GET_TICKETS_BY_USER_ID = "SELECT * FROM ticket WHERE user_id = ?";
    public static final String DELETE_TICKET_BY_USER_ID = "DELETE FROM ticket WHERE user_id = ?";
    public static final String SAVE_TICKET = "INSERT INTO ticket (ticket_type, user_id) VALUES (?::ticket_type, ?)";
    public static final String GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE id = ?";
    public static final String UPDATE_TICKET = "UPDATE ticket SET ticket_type = ?::ticket_type, user_id = ? WHERE id = ?";
    public static final String DELETE_TICKET_BY_ID = "DELETE FROM ticket WHERE id = ?";
}
