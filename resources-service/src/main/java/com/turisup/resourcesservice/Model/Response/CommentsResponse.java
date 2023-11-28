package com.turisup.resourcesservice.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsResponse {
    private Long id;
    private String content;
    private int rate;
    private UserResponse creator;
    private List<MediaResponse> media;

}
