package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.UserApp;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserApp> addUser(@RequestBody UserApp userApp){
        UserApp userApp2 = userService.addUser(userApp);
        return new ResponseEntity<>(userApp2, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserApp>> allUser(){
        List<UserApp> userApps = userService.findAll();
        return new ResponseEntity<>(userApps,HttpStatus.OK);
    }



}
