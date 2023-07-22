package com.turisup.usermicro.Service;

import com.turisup.usermicro.Model.TouristPlace;
import com.turisup.usermicro.Repository.TouristPlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TouristPlaceService {
    private final TouristPlaceRepository touristPlaceRepository;

    public TouristPlaceService(TouristPlaceRepository touristPlaceRepository) {
        this.touristPlaceRepository = touristPlaceRepository;
    }


    @Transactional
    public TouristPlace addTouristPlace(TouristPlace touristPlace){
        return touristPlaceRepository.save(touristPlace);
    }


    public List<TouristPlace> getAllTouristPlaces(){
        return touristPlaceRepository.findAll();
    }

}
