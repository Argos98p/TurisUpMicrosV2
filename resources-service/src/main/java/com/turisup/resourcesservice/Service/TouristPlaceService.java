package com.turisup.resourcesservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.turisup.resourcesservice.Model.*;
import com.turisup.resourcesservice.Model.Dao.OrganizationDao;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Repository.OficialMediaRepository;
import com.turisup.resourcesservice.Repository.TouristPlaceRepository;
import com.turisup.resourcesservice.utils.PolygonChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.lang.reflect.Type;
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

    @Autowired
    RegionService regionService;

    public TouristPlaceService(TouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }


    @Transactional
    public TouristPlace addTouristPlace(TouristPlaceDao touristPlaceDao, MultipartFile[] files, User user){

        List<Region> allRegions = regionService.allRegions();

        Region regionVinculated=null;

        for(Region region : allRegions){


            ObjectMapper objectMapper = new ObjectMapper();
            try {

                List<Coordinate> coordinatesList = objectMapper.readValue(region.getCoordinates(), new TypeReference<List<Coordinate>>() {});

                // Ahora 'coordinatesList' contiene la lista de coordenadas
                System.out.println("Size ...");
                System.out.println(coordinatesList.size());

                if(PolygonChecker.pointInPolygon(new Coordinate(touristPlaceDao.getLatitude(), touristPlaceDao.getLongitude()),coordinatesList )){
                    System.out.println("Recurso de la region");
                    System.out.println(region.getName());
                    regionVinculated=region;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }




        }






        TouristPlace auxTouristPlace =  new TouristPlace();
        auxTouristPlace.setComments(new ArrayList<>());
        auxTouristPlace.setTitle(touristPlaceDao.getTitle());
        auxTouristPlace.setDescription(touristPlaceDao.getDescription());
        auxTouristPlace.setCreator(user);
        auxTouristPlace.setOficialMedia(new ArrayList<>());
        auxTouristPlace.setLatitude(touristPlaceDao.getLatitude());
        auxTouristPlace.setLongitude(touristPlaceDao.getLongitude());
        auxTouristPlace.setStatus(touristPlaceDao.getStatus());
        if(regionVinculated!=null){
            auxTouristPlace.setRegion(regionVinculated);
        }


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
