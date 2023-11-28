package com.turisup.resourcesservice.Model;

import com.turisup.resourcesservice.Model.Dao.CommentDao;
import com.turisup.resourcesservice.Model.Response.CommentsResponse;
import com.turisup.resourcesservice.Model.Response.MediaResponse;
import com.turisup.resourcesservice.Model.Response.UserResponse;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Node
@Data

public class Comment {
    @Id
    @GeneratedValue
    Long id;
    private String content;
    private int rate;

    @Relationship(type = "CREATED_BY",direction = Relationship.Direction.OUTGOING)
    private UserApp creator;

    @Relationship(type = "MEDIA", direction = Relationship.Direction.OUTGOING)
    private List<Media> media;


    public CommentsResponse toCommentResponse (Comment comment){

        List<MediaResponse> mediaResponse = comment.getMedia()
                .stream()
                .map(media -> new MediaResponse(media.getId(), media.getUrl()))
                .collect(Collectors.toList());
        UserResponse userResponse = new UserResponse(comment.getCreator().getId(), comment.getCreator().getEmail(),comment.getCreator().getUserName(),comment.getCreator().getImageUrl());
        return  CommentsResponse.builder().id(comment.getId()).content(comment.getContent())
                .creator(userResponse).rate(comment.getRate()).media(mediaResponse).build();
    }
}
