package com.turisup.resourcesservice.Service;


import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User addUser(User user){
        return userRepository.save(user);
    }


    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
