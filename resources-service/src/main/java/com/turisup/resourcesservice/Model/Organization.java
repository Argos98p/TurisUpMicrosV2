package com.turisup.resourcesservice.Model;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Organization {
    @Id
    @GeneratedValue Long id;

    String name;
    String description;

    String logo;

    @Relationship(type = "MEMBER",direction = Relationship.Direction.OUTGOING)
    List<UserApp> members;

    @Relationship(type = "MANAGED", direction = Relationship.Direction.OUTGOING)
    List<Region> managedRegions ;


    public void addMember(UserApp userApp) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(userApp);
    }

    public void removeMember (UserApp userApp){
        members.remove(userApp);
    }



    public void setMembers(List<UserApp> members) {
        this.members = members;
    }

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<UserApp> getMembers() {
        return members;
    }

    public List<Region> getManagedRegions() {
        return managedRegions;
    }

    public void setManagedRegions(List<Region> managedRegions) {
        this.managedRegions = managedRegions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

