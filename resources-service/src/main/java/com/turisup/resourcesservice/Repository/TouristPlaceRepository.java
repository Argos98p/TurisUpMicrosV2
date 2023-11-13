package com.turisup.resourcesservice.Repository;
import com.turisup.resourcesservice.Model.TouristPlace;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TouristPlaceRepository extends Neo4jRepository<TouristPlace, Long> {
}
