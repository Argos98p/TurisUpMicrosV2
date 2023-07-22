package com.turisup.usermicro.Repository;

import com.turisup.usermicro.Model.Region;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RegionRepository extends Neo4jRepository<Region,String> {

}
