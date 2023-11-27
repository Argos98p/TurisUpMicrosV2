package com.turisup.resourcesservice.Service;


import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Model.UserApp;
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
    public UserApp addUser(UserApp userApp){
        return userRepository.save(userApp);
    }

    /*
    @Transactional
    public User updateUser (User user){return  userRepository.update(
            user.getUserName(),
            user.getEmail(),
            user.getRole(),
            user.getImageUrl()
    );}*/


    public Optional<UserApp> findById(Long id){
        return userRepository.findById(id);
    }

    public List<UserApp> findAll(){
        return userRepository.findAll();
    }

    public boolean addFavorite (Long userId, Long placeId){

        return userRepository.addFavoritePlace(userId, placeId);
    }

    // Método para eliminar un lugar turístico de la lista de favoritos del usuario
    public boolean removeFavoritePlace(Long userId, Long placeId) {
        return userRepository.removeFavoritePlace(userId, placeId);
    }

    public boolean isFavoritePlace(Long userId, Long placeId){
        try{
            return userRepository.isFavoritePlace(userId, placeId);
        } catch (Exception e ){
            return false;
        }

    }

    public List<Long> allFavorites(Long userId){
        return userRepository.getFavoritePlaces(userId);
    }

    public List<TouristPlace> allFavorites1(Long userId){
        return userRepository.getFavoritePlaces1(userId);
    }
}
