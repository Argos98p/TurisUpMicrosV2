package com.turisup.resourcesservice.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
public class Media {
    @Id
    @GeneratedValue
    Long id;
    String url;
    @Relationship(type = "OWNER", direction = Relationship.Direction.OUTGOING)
    UserApp userApp;
}
