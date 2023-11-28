package com.turisup.resourcesservice.Service;

import com.turisup.resourcesservice.Model.*;
import com.turisup.resourcesservice.Model.Dao.CommentDao;
import com.turisup.resourcesservice.Model.Response.CommentsResponse;
import com.turisup.resourcesservice.Repository.CommentRepository;
import com.turisup.resourcesservice.Repository.MediaRepository;
import com.turisup.resourcesservice.Repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public Comment addComment(CommentDao commentDao, MultipartFile[] files){

        UserApp userApp = userService.findById(commentDao.getUserId()).orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Usuario no encontrado"));
        TouristPlace touristPlace = touristPlaceRepository.findById(commentDao.getTouristPlaceId()).orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Recurso no encontrado"));

        Comment myComment = new Comment();
        myComment.setContent(commentDao.getContent());
        myComment.setRate(commentDao.getRate());
        myComment.setCreator(userApp);
        myComment.setMedia(new ArrayList<>());

        Comment commentAux = commentRepository.save(myComment);

        for (MultipartFile file : files) {
            String routeFile = fileStorageService.storeFile(file,"tourist_places/"+ touristPlace.getId()+"/comments/"+commentAux.getId().toString());
            Media media1 = new Media();
            media1.setUrl(routeFile);
            media1.setUserApp(userApp);
            Media media = mediaRepository.save(media1);
            commentAux.getMedia().add(media);

        }

        Comment myNewComment = commentRepository.save(commentAux);
        touristPlace.getComments().add(myNewComment);
        double currentRate= touristPlace.getRate();
        double newRate = commentDao.getRate();
        if(currentRate != 999.0){
            newRate = (newRate + currentRate )/2;
        }
        touristPlace.setRate(newRate);
        touristPlaceRepository.save(touristPlace);
        return myNewComment;
    }

    public List<CommentsResponse> getAllCommentsInPlace(Long touristPlaceId){
        List<CommentsResponse> commentsResponseList = new ArrayList<>();
        TouristPlace touristPlace = touristPlaceRepository.findById(touristPlaceId).orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Recurso no encontrado"));
        List<Comment> comments = touristPlace.getComments();
        for(Comment comment: comments){
            commentsResponseList.add(comment.toCommentResponse(comment));
        }
        return commentsResponseList;

    }
}
