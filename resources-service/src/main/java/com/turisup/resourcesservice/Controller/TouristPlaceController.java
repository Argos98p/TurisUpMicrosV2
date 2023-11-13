package com.turisup.resourcesservice.Controller;

import com.google.gson.Gson;
import com.turisup.resourcesservice.Model.Comment;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.Tag;
import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.UserRepository;
import com.turisup.resourcesservice.Service.TagService;
import com.turisup.resourcesservice.Service.TouristPlaceService;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class TouristPlaceController {

    TouristPlaceService touristPlaceService;
    UserService userService;

    TagService tagService;

    public TouristPlaceController(TouristPlaceService touristPlaceService, UserService userService, TagService tagService) {
        this.touristPlaceService = touristPlaceService;
        this.userService = userService;
        this.tagService = tagService;
    }

   // @PostMapping("")
    @RequestMapping(path = "/place/save", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<TouristPlace> addTouristPlace(@RequestParam("resource") String jsonString, @RequestParam("files") MultipartFile[] files     /*@RequestBody TouristPlaceDao touristPlaceDao*/){



        Gson g = new Gson();
        TouristPlaceDao touristPlaceDao = g.fromJson(jsonString, TouristPlaceDao.class);

        User user = userService.findById(touristPlaceDao.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));



       List<Tag> tags = new ArrayList<Tag>();
      /* for(int i = 0;i<touristPlaceDao.getTagsIds().size();i++){
           Tag tag = tagService.findById(touristPlaceDao.getTagsIds().get(i))
                   .orElseThrow(() -> new RuntimeException("El tag no fue encontrado"));
           tags.add(tag);
       }*/





        TouristPlace touristPlace2 = touristPlaceService.addTouristPlace(touristPlaceDao,files, user);


        return new ResponseEntity<>(touristPlace2, HttpStatus.OK);
    }

    @GetMapping("/place/all")
    public ResponseEntity<List<TouristPlace>> allTouristPlace(){
        List<TouristPlace> touristPlaces = touristPlaceService.getAllTouristPlaces();
        return new ResponseEntity<>(touristPlaces,HttpStatus.OK);
    }

    @GetMapping("/place/{id}")
    public ResponseEntity<TouristPlace> getTouristPlaceById(@PathVariable Long id){
        TouristPlace touristPlace =  touristPlaceService.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado"));
        return new ResponseEntity<>(touristPlace,HttpStatus.OK);
    }

    @GetMapping("/place/{id}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long id){
        List<Comment> comments = touristPlaceService.getAllCommentsFromId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

}
