package com.turisup.resourcesservice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
