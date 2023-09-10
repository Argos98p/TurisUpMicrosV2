package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.Tag;
import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.UserRepository;
import com.turisup.resourcesservice.Service.TagService;
import com.turisup.resourcesservice.Service.TouristPlaceService;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TouristPlaceController {

    TouristPlaceService touristPlaceService;
    UserService userService;

    TagService tagService;

    public TouristPlaceController(TouristPlaceService touristPlaceService, UserService userService, TagService tagService) {
        this.touristPlaceService = touristPlaceService;
        this.userService = userService;
        this.tagService = tagService;
    }

    @PostMapping("/touristplace/save")
    public ResponseEntity<TouristPlace> addTouristPlace(@RequestBody TouristPlaceDao touristPlaceDao){

       User user = userService.findById(touristPlaceDao.getUserId())
              .orElseThrow(() -> new RuntimeException("El usuario no fue encontrado"));

       List<Tag> tags = new ArrayList<Tag>();
       for(int i = 0;i<touristPlaceDao.getTagsIds().size();i++){
           Tag tag = tagService.findById(touristPlaceDao.getTagsIds().get(i))
                   .orElseThrow(() -> new RuntimeException("El tag no fue encontrado"));
           tags.add(tag);
       }

       TouristPlace aux = new TouristPlace(user,touristPlaceDao.getTitle(),touristPlaceDao.getDescription(),tags);
       aux.setCreator(user);

        TouristPlace touristPlace2 = touristPlaceService.addTouristPlace(aux);
        return new ResponseEntity<>(touristPlace2, HttpStatus.OK);
    }

    @GetMapping("/touristplace/all")
    public ResponseEntity<List<TouristPlace>> allTouristPlace(){
        List<TouristPlace> touristPlaces = touristPlaceService.getAllTouristPlaces();
        return new ResponseEntity<>(touristPlaces,HttpStatus.OK);
    }

}
