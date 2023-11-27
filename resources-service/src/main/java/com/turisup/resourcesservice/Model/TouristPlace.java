package com.turisup.resourcesservice.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("TouristPlace")
@Data
public class TouristPlace {
    @Id
    @GeneratedValue
    private Long id;
    @Relationship(type = "CREATED_BY",direction = Relationship.Direction.OUTGOING)
    private UserApp creator;
    private String title;
    private String description;
    //@Relationship(type = "HAS_TAG",direction = Relationship.Direction.OUTGOING)
    //private List<Tag> tags;

    @Relationship(type = "HAS_OFFICIAL_MEDIA",direction = Relationship.Direction.OUTGOING)
    public List<OficialMedia> oficialMedia;

    @Relationship(type = "COMMENTS", direction = Relationship.Direction.OUTGOING)
    public List<Comment> comments;

    private double latitude;
    private double longitude;

    private String status;

    @Relationship(type="LOCATED_IN", direction = Relationship.Direction.OUTGOING)
    public Region region;



}
