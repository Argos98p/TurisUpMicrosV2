package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.OficialMedia;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OficialMediaRepository extends Neo4jRepository<OficialMedia, Long> {
}
