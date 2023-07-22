package com.turisup.usermicro.Controller;

import com.turisup.usermicro.Model.Organization;
import com.turisup.usermicro.Service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @PostMapping("/api/v2/organization/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization){
        Organization myOrganization = organizationService.addOrganization(organization);
        return new ResponseEntity<>(myOrganization, HttpStatus.CREATED);
    }

    @GetMapping("/api/v2/organization/all")
    public ResponseEntity<List<Organization>> allOrganizations(){
        List<Organization> organizationList = organizationService.allOrganization();
        return new ResponseEntity<>(organizationList,HttpStatus.OK);
    }
}
