package com.bms.bookmyshow.service;

import java.util.List;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.bookmyshow.model.User;
import com.bms.bookmyshow.repository.userRepo;

@Service
public class UserService {  

    @Autowired
    private  userRepo userRepository;
   

    public List<User> getUsers(){   
        List<User>users=userRepository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("No users found");
        }
        return users;
    }  

    public void createUser(User user){
        if(user.getName()==null || user.getEmail()==null || user.getPassword()==null){
            throw new RuntimeException("Users details can not be empty");
        }  
    userRepository.save(user);
    }

    public User getUserById(Long id) {  
        if(id == null) {
            throw new RuntimeException("User ID cannot be null");
        }  
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    } 

    public void deleteUser(Long id) {
        if(id == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        if(id == null || user == null) {
            throw new RuntimeException("User ID and user details cannot be null");
        }
        Optional<User> existingUserOpt = userRepository.findById(id);
        if(existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }   
    }

    public boolean checkuser(User user){
        if(user.getEmail() == null || user.getPassword() == null) {
            throw new RuntimeException("Email and password cannot be null");
        }
        String email = user.getEmail();
        List<User> users = userRepository.findAll(); 
        for(User existingUser:users){
            if(existingUser.getEmail().equals(user.getEmail()) && existingUser.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public Long getUserIdByEmail(String email) {
       if(email==null) throw new RuntimeException("Email Id is not entered");
       Optional<User> user= userRepository.findByEmail(email);
       if(user.isPresent()){
          Long userId=user.get().getId();
          return userId;
       } else{
        throw new RuntimeException("EMAIL id is not present in the database");
       }
    }


}
