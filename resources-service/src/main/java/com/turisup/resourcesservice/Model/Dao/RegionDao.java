package com.turisup.resourcesservice.Model.Dao;



import lombok.Data;

@Data
public class RegionDao {
    String name;
    String description;
    double[][] coordinates;
    String organizationid;

    public RegionDao(String name, String description, double[][] coordinates, String organizationid) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.organizationid = organizationid;
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
    public String getOrganizationid() {
        return organizationid;
    }
    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid;
    }
    public double[][] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }
}

