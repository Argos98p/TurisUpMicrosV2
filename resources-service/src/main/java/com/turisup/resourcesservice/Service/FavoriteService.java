package com.turisup.resourcesservice.Service;

import com.turisup.resourcesservice.Model.Response.TouristPlaceResponse;
import com.turisup.resourcesservice.Model.TouristPlace;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    UserService userService;

    @Autowired
    TouristPlaceService touristPlaceService;

    public boolean addFavoriteToruistPlace(Long userId, Long placeId){

        return userService.addFavorite(userId, placeId);
    }

    public boolean removeFavoriteToruistPlace(Long userId, Long placeId){
        return userService.removeFavoritePlace(userId, placeId);
    }

    public boolean isFavoritePlace(Long userId, Long placeId){

        try {
            return  userService.isFavoritePlace(userId, placeId);
        }catch ( Exception e){
            return false;
        }

    }

    public List<TouristPlaceResponse> allFavoritesUser(Long userId){
        List<Long> ids = userService.allFavorites(userId);
        List<TouristPlaceResponse> touristPlaceResponses = new ArrayList<>();
        for(Long id : ids ){

            Optional<TouristPlace> touristPlaceOptional = touristPlaceService.findById(id);
            if(touristPlaceOptional.isPresent()){
                TouristPlaceResponse touristPlaceResponse = touristPlaceService.ToResponseModel(userId, touristPlaceOptional.get());
                touristPlaceResponses.add(touristPlaceResponse);
            }
        }
        return touristPlaceResponses;
    }

    public List<TouristPlace> allFavoritesUser1(Long userId){
        return userService.allFavorites1(userId);
    }
}
