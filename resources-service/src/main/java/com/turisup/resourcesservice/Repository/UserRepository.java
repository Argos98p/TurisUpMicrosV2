package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface UserRepository extends Neo4jRepository<User, String > {

}
