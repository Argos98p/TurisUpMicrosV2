package com.turisup.resourcesservice.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@Node("UserApp")

public class UserApp {
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
