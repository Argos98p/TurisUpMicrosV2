package com.turisup.usermicro.Repository;

import com.turisup.usermicro.Model.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagRepository extends Neo4jRepository<Tag, String> {
}
