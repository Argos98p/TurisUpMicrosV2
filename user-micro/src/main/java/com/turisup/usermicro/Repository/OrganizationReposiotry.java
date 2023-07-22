package com.turisup.usermicro.Repository;

import com.turisup.usermicro.Model.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrganizationReposiotry extends Neo4jRepository<Organization,String> {
}
