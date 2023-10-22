package com.turisup.resourcesservice.Controller;


import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
