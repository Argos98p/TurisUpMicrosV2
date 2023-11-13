package com.turisup.resourcesservice.Controller;


import com.google.gson.Gson;
import com.turisup.resourcesservice.Model.Dao.OrganizationDao;
import com.turisup.resourcesservice.Model.Dao.TouristPlaceDao;
import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/resources")
@RestController
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    //@PostMapping("/organization/add")
    @RequestMapping(path = "/organization/add", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Organization> addOrganization(@RequestParam("organization") String jsonString , @RequestParam("logo") MultipartFile logoImage   /*@RequestBody Organization organization*/){
        Gson g = new Gson();
        OrganizationDao organizationDao = g.fromJson(jsonString, OrganizationDao.class);
        Organization organization = new Organization(organizationDao.getName(), organizationDao.getDescription());
        organization.setManagedRegions(new ArrayList<>());
        organization.setMembers(new ArrayList<>());
        return new ResponseEntity<>(organizationService.addOrganization(organization, logoImage), HttpStatus.CREATED);
    }

    @PostMapping("/organization/addUser")
    public ResponseEntity<Organization> addUserToOrganization(@RequestParam("userId") Long userId, @RequestParam("organizationId") Long orgId){

        return new ResponseEntity<>(organizationService.addUserToOrganization(userId, orgId), HttpStatus.OK);
    }

    @PostMapping("/organization/removeUser")
    public ResponseEntity<Organization> removeUserToOrganization(@RequestParam("userId") Long userId, @RequestParam("organizationId") Long orgId){
        return new ResponseEntity<>(organizationService.removeUserToOrganization(userId, orgId), HttpStatus.OK);
    }

    @GetMapping("/organization/all")
    public ResponseEntity<List<Organization>> allOrganizations( HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        // Verificar si el encabezado de autorización está presente y es un token Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extraer el token Bearer
            String token = authorizationHeader.substring(7); // Remueve "Bearer "
            System.out.println(token);
            // Aquí puedes hacer lo que necesites con el token
        }
        List<Organization> organizationList = organizationService.allOrganization();
        return new ResponseEntity<>(organizationList,HttpStatus.OK);
    }
}
