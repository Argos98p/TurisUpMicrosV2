package com.turisup.resourcesservice.Controller;


import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/resources")
@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @PostMapping("/organization/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization){
        Organization myOrganization = organizationService.addOrganization(organization);
        return new ResponseEntity<>(myOrganization, HttpStatus.CREATED);
    }

    @GetMapping("/organization/all")
    public ResponseEntity<List<Organization>> allOrganizations(){
        List<Organization> organizationList = organizationService.allOrganization();
        return new ResponseEntity<>(organizationList,HttpStatus.OK);
    }
}
