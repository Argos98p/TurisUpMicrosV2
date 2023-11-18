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

    private String status;
   // private List<String> tagsIds;



}
