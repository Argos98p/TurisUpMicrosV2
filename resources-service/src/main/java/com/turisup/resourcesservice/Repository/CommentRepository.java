package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepository extends Neo4jRepository<Comment, Long> {
}
