package com.turisup.usermicro.Controller;

import com.turisup.usermicro.Dao.TouristPlaceDao;
import com.turisup.usermicro.Model.Tag;
import com.turisup.usermicro.Model.TouristPlace;
import com.turisup.usermicro.Model.User;
import com.turisup.usermicro.Repository.UserRepository;
import com.turisup.usermicro.Service.TagService;
import com.turisup.usermicro.Service.TouristPlaceService;
import com.turisup.usermicro.Service.UserService;
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

    @PostMapping("/api/v2/touristplace/add")
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

    @GetMapping("/api/v2/touristplace/all")
    public ResponseEntity<List<TouristPlace>> allTouristPlace(){
        List<TouristPlace> touristPlaces = touristPlaceService.getAllTouristPlaces();
        return new ResponseEntity<>(touristPlaces,HttpStatus.OK);
    }

}
