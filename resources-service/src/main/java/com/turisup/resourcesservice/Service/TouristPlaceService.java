package com.turisup.resourcesservice.Service;

import com.turisup.resourcesservice.Model.Comment;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.OficialMedia;
import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.OficialMediaRepository;
import com.turisup.resourcesservice.Repository.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TouristPlaceService {
    private final TouristPlaceRepository touristPlaceRepository;
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    OficialMediaRepository oficialMediaRepository;

    public TouristPlaceService(TouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }


    @Transactional
    public TouristPlace addTouristPlace(TouristPlaceDao touristPlaceDao, MultipartFile[] files, User user){

        TouristPlace auxTouristPlace =  new TouristPlace();
        auxTouristPlace.setComments(new ArrayList<>());
        auxTouristPlace.setTitle(touristPlaceDao.getTitle());
        auxTouristPlace.setDescription(touristPlaceDao.getDescription());
        auxTouristPlace.setCreator(user);
        auxTouristPlace.setOficialMedia(new ArrayList<>());
        auxTouristPlace.setLatitude(touristPlaceDao.getLatitude());
        auxTouristPlace.setLongitude(touristPlaceDao.getLongitude());

        //obtengo id para guardar las imagenes
        TouristPlace newTouristPlace = touristPlaceRepository.save(auxTouristPlace);


        for (MultipartFile file : files) {
            String routeFile = fileStorageService.storeFile(file,"tourist_places/"+ newTouristPlace.getId().toString());
            OficialMedia oficialMediaAux = new OficialMedia();
            oficialMediaAux.setUrl(routeFile);
            oficialMediaAux.setUser(user);
            OficialMedia oficialMedia = oficialMediaRepository.save(oficialMediaAux);
            newTouristPlace.getOficialMedia().add(oficialMedia);
            //oficialMediaRepository.save(newTouristPlace);
            System.out.println(routeFile);

        }

        return touristPlaceRepository.save(newTouristPlace);



    }


    public List<TouristPlace> getAllTouristPlaces(){
        return touristPlaceRepository.findAll();
    }

    public Optional<TouristPlace> findById(Long id){
        return touristPlaceRepository.findById(id);
    }

    public List<Comment> getAllCommentsFromId( Long id){
        TouristPlace touristPlace = touristPlaceRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Place no encontrado"));
        return  touristPlace.getComments();

    }



    /*
    public TouristPlace updateListImages (ArrayList<String> newImages){

    }

    public TouristPlace updateListVideos (ArrayList<String> newImages){

    }*/

}
