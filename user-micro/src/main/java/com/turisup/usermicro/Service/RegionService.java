package com.turisup.usermicro.Service;

import com.turisup.usermicro.Model.Region;
import com.turisup.usermicro.Repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional
    public Region addRegion(Region newRegion){
        return regionRepository.save(newRegion);
    }

    public List<Region> allRegions(){
        return regionRepository.findAll();
    }
}
