package com.turisup.authservice.repository;

import com.turisup.authservice.entity.AuthUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends Neo4jRepository<AuthUser,Long>  {
    Optional<AuthUser> findByUserName(String username);
    Optional<AuthUser> findByEmail(String email);
}
