package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.UserRepository;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/resources/user")
@RestController
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User user2 = userService.addUser(user);
        return new ResponseEntity<>(user2, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUser(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }



}
