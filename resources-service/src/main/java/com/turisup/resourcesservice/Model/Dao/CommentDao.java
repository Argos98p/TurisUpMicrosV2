package com.turisup.resourcesservice.Model.Dao;

import lombok.Data;

@Data
public class CommentDao {
    Long userId;
    Long touristPlaceId;
    String content;
    int rate;
}


