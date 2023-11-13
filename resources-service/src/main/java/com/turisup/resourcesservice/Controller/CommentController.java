package com.turisup.resourcesservice.Controller;

import com.google.gson.Gson;
import com.turisup.resourcesservice.Model.Comment;
import com.turisup.resourcesservice.Model.Dao.CommentDao;
import com.turisup.resourcesservice.Model.Dao.OrganizationDao;
import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Repository.TouristPlaceRepository;
import com.turisup.resourcesservice.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/resources")
public class CommentController {

    @Autowired
    CommentService commentService;



    @RequestMapping(path = "/comment/add", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Comment> addComment(@RequestParam("comment") String jsonString , @RequestParam("files") MultipartFile[] files  /*@RequestBody Organization organization*/){
        Gson g = new Gson();
        CommentDao commentDao = g.fromJson(jsonString, CommentDao.class);

        return new ResponseEntity<>(commentService.addComment(commentDao,files), HttpStatus.CREATED);
    }

    @GetMapping("/comment/all")
    public ResponseEntity<Comment> getAllComments(@RequestParam("touristPlaceId") Long touristPlaceId){



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
