package com.turisup.resourcesservice.Service;

import com.turisup.resourcesservice.Model.Coordinate;
import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Model.Region;
import com.turisup.resourcesservice.Repository.OrganizationReposiotry;
import com.turisup.resourcesservice.Repository.RegionRepository;
import com.turisup.resourcesservice.utils.PolygonChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RegionService {
    private final RegionRepository regionRepository;
    @Autowired
    OrganizationReposiotry organizationReposiotry;
    @Autowired
    OrganizationService organizationService;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional
    public Region addRegion(List<Coordinate> coordinates, Region newRegion, Long organizationId){

        Organization organization = organizationService.findById(organizationId).orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Organizacion no encontrada"));
        if(!PolygonChecker.isPolygonClosed(coordinates)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de coordenadas no valida");
        }

        Region region = regionRepository.save(newRegion);
        organization.getManagedRegions().add(region);
        organizationReposiotry.addRegionToOrganization(organizationId,region.getId());
        organizationReposiotry.save(organization);
        //region.setOrganization(organization);
        //organizationReposiotry.save(organization);



        return regionRepository.save(region);
    }

    public List<Region> allRegions(){
        return regionRepository.findAll();
    }

    @Transactional
    public Boolean deleteRegion(Long idRegion){
        Region myRegion = regionRepository.findById(idRegion).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Region no encontrada"));
        regionRepository.delete(myRegion);
        return true;
    }
}
