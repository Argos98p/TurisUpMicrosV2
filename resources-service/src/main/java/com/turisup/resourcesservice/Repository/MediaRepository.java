package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Media;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MediaRepository extends Neo4jRepository<Media,Long> {
}
