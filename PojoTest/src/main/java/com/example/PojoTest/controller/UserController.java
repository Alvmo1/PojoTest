package com.example.PojoTest.controller;

import com.example.PojoTest.modles.User;
import com.example.PojoTest.repos.UserRepository;
import com.example.PojoTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //make user
    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    //get all users
    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    //get user by id
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        userService.getUserById(userId);
        return ResponseEntity.ok().build();
    }
    //update user by id
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long userId){
        return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
    }
    //delete user by id
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
