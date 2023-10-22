package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.Dao.UpdateUserDto;
import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/resources/admin")
@RestController
public class AdminController {

    /*
    @Autowired
    public final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }*/


    /*
    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto updateUserDto, HttpServletRequest request  ){




        Optional<User> userToUpdateOpt = userService.findById(updateUserDto.getId());
        if (userToUpdateOpt.isPresent()) {
            User userToUpdate  = userToUpdateOpt.get();
            userToUpdate.setRole(updateUserDto.getRole());
            User updateUser = userService.updateUser(userToUpdate);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } else {
            System.out.println("El valor no está presente");
        }
        return ResponseEntity.notFound().build();


    }
*/
}
