package com.turisup.resourcesservice.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    private String content;
    private int rate;

    @Relationship(type = "CREATED_BY",direction = Relationship.Direction.OUTGOING)
    private User creator;

    @Relationship(type = "MEDIA", direction = Relationship.Direction.OUTGOING)
    private List<Media> media;
}
