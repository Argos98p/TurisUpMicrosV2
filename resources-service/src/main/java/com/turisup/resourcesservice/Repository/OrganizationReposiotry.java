package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationReposiotry extends Neo4jRepository<Organization,Long> {

    @Query("MATCH (o:Organization), (r:Region) " +
            "WHERE id(o) = $organizationId AND id(r) = $regionId " +
            "CREATE (r)-[:BELONGS_TO]->(o)")
    void addRegionToOrganization(@Param("organizationId") Long organizationId, @Param("regionId") Long regionId);

}
