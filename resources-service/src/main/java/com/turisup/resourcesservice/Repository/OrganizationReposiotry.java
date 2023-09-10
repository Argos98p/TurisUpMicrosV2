package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrganizationReposiotry extends Neo4jRepository<Organization,String> {
}
