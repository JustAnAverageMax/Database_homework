package com.tvm.Service;

import com.tvm.DAO.UserDAO;
import com.tvm.Model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public List<Integer> getAllUsersIds(){
        try {
           return userDAO.getAllIds();
        }catch (SQLException ex){
            ex.printStackTrace();
            return List.of();
        }
    }

    public List<User> getAllUsers(){
        try {
            return userDAO.getAll();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return List.of();
    }

    public void save(User user){
        try{
            userDAO.save(user);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public User getById(int id){
        User result = new User();
        try{
            Optional<User> userOpt = userDAO.getById(id);
            if(userOpt.isPresent()){
                result = userOpt.get();
            }else{
                System.out.println("No such ticket found");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public void update(int id, User user){
        try{
            userDAO.update(id, user);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteById(int id){
        try{
            userDAO.deleteById(id);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
