package com.turisup.resourcesservice.Model.Dao;

import lombok.Data;

import java.util.List;


@Data
public class TouristPlaceDao {

    private String title;
    private String description;
    private Long userId;
    private double latitude;
    private double longitude;
   // private List<String> tagsIds;



}
