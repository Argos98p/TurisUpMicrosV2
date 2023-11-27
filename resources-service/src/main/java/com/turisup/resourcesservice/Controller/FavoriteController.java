package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.Response.TouristPlaceResponse;
import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping(path = "/favorite/add")
    public ResponseEntity<?> addFavorite(@RequestParam("userId") Long id, @RequestParam("placeId") Long placeId ){

        boolean result = favoriteService.addFavoriteToruistPlace(id,placeId);

        if (result) {
            return new ResponseEntity<>("Favorite place added successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add favorite place (relationship already exists or nodes not found)", HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping(path = "/favorite/remove")
    public ResponseEntity<?> removeFavorite(@RequestParam("userId") Long id, @RequestParam("placeId") Long placeId ){

        if (favoriteService.isFavoritePlace(id, placeId)) {
            boolean result = favoriteService.removeFavoriteToruistPlace(id, placeId);
            if(result){
                return new ResponseEntity<>("Favorite place removed successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Failed to remove favorite place (relationship not found or nodes not found)", HttpStatus.BAD_REQUEST);

            }

        } else {
            return new ResponseEntity<>("Failed to remove favorite place (relationship not found or nodes not found)", HttpStatus.BAD_REQUEST);
        }





    }

    @GetMapping(path = "/favorites")
    public ResponseEntity<List<TouristPlaceResponse>> allFavorites(@RequestParam("userId") Long id ){

        return new ResponseEntity<>( favoriteService.allFavoritesUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/favorites1")
    public ResponseEntity<List<TouristPlace>> allFavorites1(@RequestParam("userId") Long id ){

        return new ResponseEntity<>( favoriteService.allFavoritesUser1(id), HttpStatus.OK);
    }
}
