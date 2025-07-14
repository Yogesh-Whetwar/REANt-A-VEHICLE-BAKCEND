package com.bms.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.bookmyshow.model.User;
import com.bms.bookmyshow.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
      
@Autowired
private UserService userService;

@PostMapping("/create")
public void createUser(@RequestBody User user){
    userService.createUser(user);
}  

@GetMapping("/getAll")
public ResponseEntity<List<User>> getUsers(){
     List<User>users=userService.getUsers();
     return ResponseEntity.ok(users);
}  

@GetMapping("/get/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id){
    User user = userService.getUserById(id);
    return ResponseEntity.ok(user);
}

@DeleteMapping("/delete/{id}")
public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    ResponseEntity.ok("User deleted successfully");     
}  

@PutMapping("/update/{id}")
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {   

    //passing id and user object to service layer
    User updatedUser = userService.updateUser(id, user);
    return ResponseEntity.ok(updatedUser);  
}  

@PostMapping("/check")
public boolean checkUser(@RequestBody User user){
     boolean res=userService.checkuser(user);
     return res;
}

@GetMapping("/getProfileIdByEmail/{email}")
public ResponseEntity<Long> getUserByEmail(@PathVariable String email) {
    Long id = userService.getUserIdByEmail(email);
    return ResponseEntity.ok(id);

}      


}
