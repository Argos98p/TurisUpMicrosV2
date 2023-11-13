package com.turisup.resourcesservice.Model;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Region {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String description;
   // @Relationship(type = "has_coordinate",direction = Relationship.Direction.OUTGOING)
    //List<Coordinate> coordinates;
    String coordinates;

    //@Relationship(type = "BELONGS_TO",direction = Relationship.Direction.OUTGOING)
    //Organization organization;

  /* public Organization getOrganization() {
        return organization;
    }
    */

/*
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }*/


    public Region(String name, String description, String coordinates) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;

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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }


    public String coordinatesToJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convierte la lista de coordenadas a una cadena JSON
            return objectMapper.writeValueAsString(this.coordinates);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Manejo de errores, puedes personalizar esto según tus necesidades
            return null;
        }
    }

}
