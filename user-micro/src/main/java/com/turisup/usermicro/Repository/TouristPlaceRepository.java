package com.turisup.usermicro.Repository;
import com.turisup.usermicro.Model.TouristPlace;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TouristPlaceRepository extends Neo4jRepository<TouristPlace, String> {
}
