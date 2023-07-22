package com.turisup.usermicro.Service;

import com.turisup.usermicro.Model.Organization;
import com.turisup.usermicro.Model.User;
import com.turisup.usermicro.Repository.OrganizationReposiotry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationReposiotry organizationReposiotry;

    public OrganizationService(OrganizationReposiotry organizationReposiotry) {
        this.organizationReposiotry = organizationReposiotry;
    }
    @Transactional
    public Organization addOrganization(Organization newOrg){
        return organizationReposiotry.save(newOrg);
    }

    public List<Organization> allOrganization(){
        return organizationReposiotry.findAll();
    }

    public Optional<Organization> findById(String id){
        return organizationReposiotry.findById(id);
    }
}
