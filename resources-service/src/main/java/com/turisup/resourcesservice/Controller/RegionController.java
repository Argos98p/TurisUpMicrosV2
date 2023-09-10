package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.Dao.RegionDao;
import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Model.Region;
import com.turisup.resourcesservice.Service.OrganizationService;
import com.turisup.resourcesservice.Service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class RegionController {

    private final OrganizationService organizationService;
    private final RegionService regionService;

    public RegionController(RegionService regionService, OrganizationService organizationService, RegionService regionService1) {
        this.organizationService = organizationService;
        this.regionService = regionService1;
    }
    @PostMapping("/api/v2/region/add")
    public ResponseEntity<Region> addRegion(@RequestBody RegionDao regionDao){
        System.out.println(regionDao.getName());
        Organization org = organizationService.findById(regionDao.getOrganizationid())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Organizacion no encontrada"));
        Region myRegion = regionService.addRegion(new Region(regionDao.getName(), regionDao.getDescription(),regionDao.getCoordinates(),org));

        return new ResponseEntity<>(myRegion, HttpStatus.CREATED);
    }

    @GetMapping("/api/v2/region/all")
    public ResponseEntity<List<Region>> allRegions(){
        List<Region> regiones = regionService.allRegions();
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }
}
