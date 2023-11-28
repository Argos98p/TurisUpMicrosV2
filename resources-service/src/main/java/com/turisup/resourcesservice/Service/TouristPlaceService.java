package com.turisup.resourcesservice.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turisup.resourcesservice.Model.*;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.Response.*;
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

    @Autowired
    UserService userService;

    @Autowired
    OrganizationService organizationService;
    public TouristPlaceService(TouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }


    @Transactional
    public TouristPlace addTouristPlace(TouristPlaceDao touristPlaceDao, MultipartFile[] files, UserApp userApp){

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



        TouristPlace auxTouristPlace =   TouristPlace.builder()
                .comments(new ArrayList<>()).
                title(touristPlaceDao.getTitle()).
                description(touristPlaceDao.getDescription()).
                creator(userApp).
                oficialMedia(new ArrayList<>()).
                latitude(touristPlaceDao.getLatitude()).
                longitude(touristPlaceDao.getLongitude()).
                status(touristPlaceDao.getStatus()).
                build();

        if(regionVinculated!=null){
            auxTouristPlace.setRegion(regionVinculated);
        }


        //obtengo id para guardar las imagenes
        TouristPlace newTouristPlace = touristPlaceRepository.save(auxTouristPlace);


        for (MultipartFile file : files) {
            String routeFile = fileStorageService.storeFile(file,"tourist_places/"+ newTouristPlace.getId().toString());
            OficialMedia oficialMediaAux = new OficialMedia();
            oficialMediaAux.setUrl(routeFile);
            oficialMediaAux.setUserApp(userApp);
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


     public  TouristPlaceResponse ToResponseModel(Long userId, TouristPlace touristPlace){

        List<OficialMediaResponse> oficialMediaResponses= new ArrayList<>();
        for(OficialMedia oficialMedia : touristPlace.getOficialMedia()){
            oficialMediaResponses.add(new OficialMediaResponse(oficialMedia.getId(), oficialMedia.getUrl(), oficialMedia.getUserApp().getId(),oficialMedia.getUserApp().getUserName()));
        }

        TouristPlaceResponse response = new TouristPlaceResponse(
                touristPlace.getId(),
                touristPlace.getTitle(),
                touristPlace.getDescription(),
                new Coordinate(touristPlace.getLatitude(),touristPlace.getLongitude()),
                touristPlace.getStatus(),
                new UserResponse(touristPlace.getCreator().getId(),touristPlace.getCreator().getEmail(),touristPlace.getCreator().getUserName(),touristPlace.getCreator().getImageUrl() ),
                oficialMediaResponses,
                null,
                null,
                null,
                userService.isFavoritePlace( userId, touristPlace.getId()),
                touristPlace.getRate()

        );

        if(touristPlace.getRegion()!=null){
            response.setRegion(new RegionResponse(touristPlace.getRegion().getId(),touristPlace.getRegion().getName(),""));
            Organization org = organizationService.getOrganizationByRegionId(touristPlace.getRegion().getId());
            response.setOrganization(new OrganizationResponse(org.getId(), org.getName(), org.getLogo()));

        }
        return response;
    }


    /*
    public TouristPlace updateListImages (ArrayList<String> newImages){

    }

    public TouristPlace updateListVideos (ArrayList<String> newImages){

    }*/

}
