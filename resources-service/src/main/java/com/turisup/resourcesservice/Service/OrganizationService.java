package com.turisup.resourcesservice.Service;


import com.turisup.resourcesservice.Model.Organization;
import com.turisup.resourcesservice.Model.User;
import com.turisup.resourcesservice.Repository.OrganizationReposiotry;
import com.turisup.resourcesservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    FileStorageService fileStorageService;
    private final OrganizationReposiotry organizationReposiotry;
    @Autowired
    UserService userService;
    public OrganizationService(OrganizationReposiotry organizationReposiotry) {
        this.organizationReposiotry = organizationReposiotry;
    }
    @Transactional
    public Organization addOrganization(Organization newOrg, MultipartFile imageLogo){
        Organization newOrganization =  organizationReposiotry.save(newOrg);
        String routeFile = fileStorageService.storeFile(imageLogo, "organizations/"+newOrganization.getId());
        newOrganization.setLogo(routeFile);
        return organizationReposiotry.save(newOrganization);
    }

    @Transactional
    public Organization addUserToOrganization(Long userId, Long orgId){
        System.out.println(userId);
        System.out.println(orgId);
        Organization organization = organizationReposiotry.findById(orgId).orElse(null);
        User user = userService.findById(userId).orElse(null);

        if (organization != null && user != null) {
            organization.addMember(user);
           return organizationReposiotry.save(organization);
        }
        return  null;
    }


    @Transactional
    public Organization removeUserToOrganization(Long userId, Long orgId){
        System.out.println(userId);
        System.out.println(orgId);
        Organization organization = organizationReposiotry.findById(orgId).orElse(null);
        User user = userService.findById(userId).orElse(null);

        if (organization != null && user != null) {
            organization.removeMember(user);
            return organizationReposiotry.save(organization);
        }
        return  null;
    }

    public List<Organization> allOrganization(){
        return organizationReposiotry.findAll();
    }

    public Optional<Organization> findById(Long id){
        return organizationReposiotry.findById(id);
    }

    public void addRegionToOrganization(Long organizationId, Long regionId) {
        organizationReposiotry.addRegionToOrganization(organizationId, regionId);
    }
}
