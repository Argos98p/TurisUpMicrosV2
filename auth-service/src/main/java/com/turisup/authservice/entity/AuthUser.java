package com.turisup.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Node("UserApp ")
public class AuthUser {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String userName;
    private String password;
    @Email
    private String email;
    private String role;
    private String imageUrl;
    @NotNull
    private AuthProvider provider;
    private String providerId;
    private String emailVerified;
}
