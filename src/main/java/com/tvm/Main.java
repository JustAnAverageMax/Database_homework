package com.tvm;

import com.tvm.Model.Ticket;
import com.tvm.Model.User;
import com.tvm.Service.TicketAndUserService;
import com.tvm.Service.TicketService;
import com.tvm.Service.UserService;
import com.tvm.constants.TicketType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static final TicketType[] TICKET_TYPES = TicketType.values();
    public static final List<String> NAMES;
    public static final List<String> SURNAMES;

    static{
        List<String> tempNames = Collections.emptyList();
        List<String> tempSurnames = Collections.emptyList();

        try{
            tempNames = Files.readAllLines(Paths.get("names.txt"));
            tempSurnames = Files.readAllLines(Paths.get("surnames.txt"));
        }catch(IOException ex){
            ex.printStackTrace();
        }

        NAMES = tempNames;
        SURNAMES = tempSurnames;
    }

    public static final Random RND = new Random();
    public static final TicketService ticketService = new TicketService();
    public static final UserService userService = new UserService();
    public static final TicketAndUserService ticketAndUserService = new TicketAndUserService();

    public static List<Integer> availableUsersIds = new ArrayList<>();
    public static List<Integer> availableTicketsIds = new ArrayList<>();

    public static void main(String[] args) {
        Ticket testTicket = new Ticket();
        User testUser = new User();

        for(int i = 0; i<10; i++){
            testUser.setName(getRandomName() + " " + getRandomSurname());
            userService.save(testUser);
        }

        availableUsersIds = userService.getAllUsersIds();
        int randomUserId = getRandomUserId();

        for(int i = 0; i<10; i++){
            testTicket.setTicketType(getRandomTicketType());
            testTicket.setUserId(getRandomUserId());
            ticketService.save(testTicket);
        }

        availableTicketsIds = ticketService.getAllTicketsIds();
        int randomTicketId = getRandomTicketId();

        printAllUsers();
        printAllTickets();

        System.out.println("Updating names in user table");

        for (Integer id: availableUsersIds){
            testUser.setName(getRandomName() + " " + getRandomSurname());
            userService.update(id, testUser);
        }

        printAllUsers();

        System.out.println("Updating ticket types in ticket table");

        for(Integer id: availableTicketsIds){
            testTicket.setUserId(getRandomUserId());
            testTicket.setTicketType(getRandomTicketType());
            ticketService.update(id, testTicket);
        }

        printAllTickets();

        System.out.println("Fetching tickets by random user id - " + randomUserId);
        ticketService.getTicketsByUserId(randomUserId).forEach(System.out::println);

        System.out.println("Deleting random users and their tickets (if any): ");

        for(int i = 0; i<5; i++){
            Integer idToDelete = getRandomUserId();
            userService.deleteById(idToDelete);
            availableUsersIds.remove(idToDelete);
        }

        printAllUsers();
        printAllTickets();

        randomUserId = getRandomUserId();
        testTicket.setUserId(getRandomUserId());
        testTicket.setTicketType(getRandomTicketType());
        testUser.setName(getRandomName() + " " + getRandomSurname());

        System.out.println("User before update - " + userService.getById(randomUserId));
        System.out.println("Ticket before update - " + ticketService.getById(randomTicketId));

        System.out.println("Simultaneously updating ticket #" + randomTicketId + " and user #" + randomUserId);
        ticketAndUserService.saveUserAndTicket(testUser, testTicket, randomUserId, randomTicketId);

        System.out.println("Changed user - " + userService.getById(randomUserId));
        System.out.println("Changed ticket - " + ticketService.getById(randomTicketId));

    }

    public static void printAllUsers(){
        System.out.println("Users: ");
        userService.getAllUsers().forEach(System.out::println);
    }

    public static void printAllTickets(){
        System.out.println("Tickets: ");
        ticketService.getAllTickets().forEach(System.out::println);
    }

    public static int getRandomUserId() {
        return availableUsersIds.get(RND.nextInt(availableUsersIds.size()));
    }

    public static int getRandomTicketId() {
        return availableTicketsIds.get(RND.nextInt(availableTicketsIds.size()));
    }

    public static TicketType getRandomTicketType() {
        return TICKET_TYPES[RND.nextInt(TICKET_TYPES.length)];
    }

    public static String getRandomName(){
        return NAMES.get(RND.nextInt(NAMES.size()));
    }

    public static String getRandomSurname(){
        return SURNAMES.get(RND.nextInt(NAMES.size()));
    }
}