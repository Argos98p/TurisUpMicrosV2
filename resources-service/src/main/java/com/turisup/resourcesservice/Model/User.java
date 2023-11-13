package com.turisup.resourcesservice.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Node("UserApp ")

public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    //private String password;
    @Email
    private String email;
    private String role;
    private String imageUrl;

    //private AuthProvider provider;
    private String providerId;
    private String emailVerified;
}
