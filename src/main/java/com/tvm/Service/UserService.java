package com.tvm.Service;

import com.tvm.DAO.UserDAO;
import com.tvm.Entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public List<Integer> getAllUsersIds() {
        return userDAO.getAllIds();
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public User getById(int id) {
        User result = new User();

        Optional<User> userOpt = userDAO.getById(id);
        if (userOpt.isPresent()) {
            result = userOpt.get();
        } else {
            System.out.println("No such user found");
        }

        return result;
    }

    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
