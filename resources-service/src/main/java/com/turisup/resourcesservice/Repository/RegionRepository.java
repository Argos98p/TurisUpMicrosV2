package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Region;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RegionRepository extends Neo4jRepository<Region,String> {

}
