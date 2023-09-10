package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagRepository extends Neo4jRepository<Tag, String> {
}
