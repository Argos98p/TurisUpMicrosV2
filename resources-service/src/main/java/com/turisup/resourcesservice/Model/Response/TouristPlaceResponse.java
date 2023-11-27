package com.turisup.resourcesservice.Model.Response;

import com.turisup.resourcesservice.Model.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TouristPlaceResponse {
    Long id;
    String title;
    String description;
    Coordinate coordinates;
    String status;
    UserResponse creator;
    List<OficialMediaResponse> oficialMedia;
    List<CommentsResponse> comments;
    OrganizationResponse organization;
    RegionResponse region;
    boolean isFavorite;
}
