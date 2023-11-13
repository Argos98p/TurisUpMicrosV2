package com.turisup.resourcesservice.Service;

import com.turisup.resourcesservice.Model.*;
import com.turisup.resourcesservice.Model.Dao.CommentDao;
import com.turisup.resourcesservice.Repository.CommentRepository;
import com.turisup.resourcesservice.Repository.MediaRepository;
import com.turisup.resourcesservice.Repository.OficialMediaRepository;
import com.turisup.resourcesservice.Repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TouristPlaceRepository touristPlaceRepository;

    @Autowired
    UserService userService;

    @Autowired
    MediaRepository mediaRepository;


    @Autowired
    FileStorageService fileStorageService;

    public Comment addComment(CommentDao commentDao, MultipartFile[] files){

        User user = userService.findById(commentDao.getUserId()).orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));
        TouristPlace touristPlace = touristPlaceRepository.findById(commentDao.getTouristPlaceId()).orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Recurso no encontrado"));




        Comment myComment = new Comment();
        myComment.setContent(commentDao.getContent());
        myComment.setRate(commentDao.getRate());
        myComment.setCreator(user);
        myComment.setMedia(new ArrayList<>());

        Comment commentAux = commentRepository.save(myComment);



        for (MultipartFile file : files) {
            String routeFile = fileStorageService.storeFile(file,"tourist_places/"+ touristPlace.getId()+"/comments/"+commentAux.getId().toString());
            Media media1 = new Media();
            media1.setUrl(routeFile);
            media1.setUser(user);

            Media media = mediaRepository.save(media1);
            commentAux.getMedia().add(media);

        }

        Comment myNewComment = commentRepository.save(commentAux);
        touristPlace.getComments().add(myNewComment);
        touristPlaceRepository.save(touristPlace);
        return myNewComment;
    }
}
