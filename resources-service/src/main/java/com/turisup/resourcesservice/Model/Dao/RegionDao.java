package com.turisup.resourcesservice.Model.Dao;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turisup.resourcesservice.Model.Coordinate;
import lombok.Data;

import java.util.List;

@Data
public class RegionDao {
    String name;
    String description;
    private List<Coordinate> coordinates;
    Long organizationId;

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public RegionDao(String name, String description, Long organizationid, List<Coordinate> coordinates) {
        this.name = name;
        this.description = description;
        this.organizationId = organizationid;
        this.coordinates = coordinates;
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
    public Long getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

