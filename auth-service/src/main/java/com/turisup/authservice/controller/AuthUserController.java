package com.turisup.authservice.controller;

import com.turisup.authservice.dto.AuthUserDto;
import com.turisup.authservice.dto.NewUserDto;
import com.turisup.authservice.dto.RequestDto;
import com.turisup.authservice.dto.TokenDto;
import com.turisup.authservice.entity.AuthUser;
import com.turisup.authservice.entity.User;
import com.turisup.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto){
        System.out.println("holaaaaa");
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto){

        TokenDto tokenDto = authUserService.validate(token, dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto dto){


        AuthUser authUser = authUserService.save(dto);
        if(authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> userFromToken(@RequestParam String token){
        AuthUser authUser = authUserService.getUserFromToken(token);
        User user = new User(authUser.getId(),authUser.getUserName(), authUser.getEmail(),authUser.getImageUrl(),authUser.getRole());
        if(authUser == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }


}
