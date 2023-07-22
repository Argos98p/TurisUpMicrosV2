package com.turisup.usermicro.Repository;

import com.turisup.usermicro.Model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface UserRepository extends Neo4jRepository<User, String > {

}
