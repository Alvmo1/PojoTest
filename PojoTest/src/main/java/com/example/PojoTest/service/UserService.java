package com.example.PojoTest.service;

import com.example.PojoTest.exception.ResourceNotFoundException;
import com.example.PojoTest.modles.User;
import com.example.PojoTest.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void confirmUser(Long userId) throws ResourceNotFoundException{
        if (!userRepository.existsById(userId)){
            logger.error("User not confirmed");
            throw new ResourceNotFoundException("User with id " + userId + "is not found");
        }
    }
    //add user
    public User createUser(User user) throws ResourceNotFoundException {
        logger.info("User created!!");
        return userRepository.save(user);
    }
    //get user by id
    public User getUserById(Long userId) throws ResourceNotFoundException{
        logger.info("Successfully retrieved user by id!");
        if (userId == null){
            throw new ResourceNotFoundException("Error fetching id");
        }
        return null;
    }
    //get all users
    public Iterable<User> getAllUsers() throws ResourceNotFoundException{
        logger.info("Successfully retrieved all users!!");
        Iterable<User> users = userRepository.findAll();
        if (!users.iterator().hasNext()){
            throw new ResourceNotFoundException("Error getting all users!");
        }
        return users;
    }
    //delete user
    public void deleteUser(Long userId){
        if (userId == null){
            logger.error("User not found");
            throw new ResourceNotFoundException("Error fetching user");
        }
        confirmUser(userId);
        logger.info("User has been deleted");
        userRepository.deleteById(userId);
    }
    //update user
    public User updateUser(User user, Long userId){
        confirmUser(user.getId());
        User oldUser = getUserById(user.getId());
        user.setId(oldUser.getId());
        user.setUserName(oldUser.getUserName());
        return userRepository.save(user);
    }



}
