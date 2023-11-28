package com.turisup.resourcesservice.Controller;

import com.google.gson.Gson;
import com.turisup.resourcesservice.Model.*;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.Response.*;
import com.turisup.resourcesservice.Service.OrganizationService;
import com.turisup.resourcesservice.Service.TagService;
import com.turisup.resourcesservice.Service.TouristPlaceService;
import com.turisup.resourcesservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OrganizationService organizationService;

    public TouristPlaceController(TouristPlaceService touristPlaceService, UserService userService, TagService tagService) {
        this.touristPlaceService = touristPlaceService;
        this.userService = userService;
        this.tagService = tagService;
    }

   // @PostMapping("")
    @RequestMapping(path = "/place/save", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<TouristPlaceResponse> addTouristPlace(@RequestParam("resource") String jsonString, @RequestParam("files") MultipartFile[] files     /*@RequestBody TouristPlaceDao touristPlaceDao*/){

        Gson g = new Gson();
        TouristPlaceDao touristPlaceDao = g.fromJson(jsonString, TouristPlaceDao.class);

        System.out.println(touristPlaceDao.getUserId());

        UserApp userApp = userService.findById(touristPlaceDao.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

       List<Tag> tags = new ArrayList<Tag>();
      /* for(int i = 0;i<touristPlaceDao.getTagsIds().size();i++){
           Tag tag = tagService.findById(touristPlaceDao.getTagsIds().get(i))
                   .orElseThrow(() -> new RuntimeException("El tag no fue encontrado"));
           tags.add(tag);
       }*/


        TouristPlace touristPlace2 = touristPlaceService.addTouristPlace(touristPlaceDao,files, userApp);

        List<OficialMediaResponse> oficialMediaResponses= new ArrayList<>();
        for(OficialMedia oficialMedia : touristPlace2.getOficialMedia()){
            oficialMediaResponses.add(new OficialMediaResponse(oficialMedia.getId(), oficialMedia.getUrl(), oficialMedia.getUserApp().getId(),oficialMedia.getUserApp().getUserName()));

        }

        List<CommentsResponse> commentsResponses = new ArrayList<>();

        TouristPlaceResponse response = new TouristPlaceResponse(
             touristPlace2.getId(),
                touristPlace2.getTitle(),
                touristPlace2.getDescription(),
                new Coordinate(touristPlace2.getLatitude(),touristPlace2.getLongitude()),
                touristPlace2.getStatus(),
                new UserResponse(touristPlace2.getCreator().getId(),touristPlace2.getCreator().getEmail(),touristPlace2.getCreator().getUserName(),touristPlace2.getCreator().getImageUrl() ),
                oficialMediaResponses,
                commentsResponses,
                null,
                null,
                userService.isFavoritePlace(userApp.getId(), touristPlace2.getId()),
                touristPlace2.getRate()
        );

        if(touristPlace2.getRegion()!=null){
            response.setRegion(new RegionResponse(touristPlace2.getRegion().getId(),touristPlace2.getRegion().getName(),""));
            Organization org = organizationService.getOrganizationByRegionId(touristPlace2.getRegion().getId());
            response.setOrganization(new OrganizationResponse(org.getId(), org.getName(), org.getLogo()));

        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/place/all")
    public ResponseEntity<List<TouristPlaceResponse>> allTouristPlace(@RequestParam("userId") Long userId){
        List<TouristPlace> touristPlaces = touristPlaceService.getAllTouristPlaces();
        List<TouristPlaceResponse> finalTouristPlace = new ArrayList<>();

        for (TouristPlace touristPlace : touristPlaces){
            finalTouristPlace.add(touristPlaceService.ToResponseModel(userId, touristPlace));
        }
        return new ResponseEntity<>(finalTouristPlace,HttpStatus.OK);
    }

    @GetMapping("/place/{id}")
    public ResponseEntity<TouristPlaceResponse> getTouristPlaceById(@PathVariable Long id, @RequestParam("userId") Long userId){
        TouristPlace touristPlace =  touristPlaceService.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado"));
        TouristPlaceResponse touristPlaceResponse = touristPlaceService.ToResponseModel(userId,touristPlace);
        return new ResponseEntity<>(touristPlaceResponse,HttpStatus.OK);
    }

    @GetMapping("/place/{id}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long id){
        List<Comment> comments = touristPlaceService.getAllCommentsFromId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

}
