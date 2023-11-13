package com.turisup.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Node
public class User {
    @GeneratedValue
    @Id Long id;
    String username;
    String email;
    String imageUrl;
    String role;
}
