package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends Neo4jRepository<User, Long > {

    List<User> findAll();

    /*
    @Transactional
    @Query("MATCH (u:User {userName: $userName}) SET u.email = $email, u.role = $role, u.imageUrl = $imageUrl RETURN u")
    User update(@Param("userName") String userName, @Param("email") String email, @Param("role") String role, @Param("imageUrl") String imageUrl);
*/

}
