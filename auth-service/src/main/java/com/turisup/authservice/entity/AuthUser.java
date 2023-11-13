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
@Node("UserApp")
public class AuthUser {

    @Id
    @GeneratedValue
    private Long id;
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

            /*
    public AuthUser(String id, String userName, String password, String email, String role, String imageUrl, AuthProvider provider, String providerId, String emailVerified) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
        this.emailVerified = emailVerified;
    }

    public AuthUser() {
    }

    public AuthUser(String userName, String email, String imageUrl, AuthProvider provider, String providerId, String password, String role, String emailVerified) {

        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
        this.emailVerified = emailVerified;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPassword() {
        return this.password;
    }

    public static class Builder {
        private String userName;
        private String email;
        private String imageUrl;
        private AuthProvider provider;
        private String providerId;
        private String password;
        private String role;
        private String emailVerified;

        private Builder() {
            // Constructor privado para evitar instanciación externa
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder provider(AuthProvider provider) {
            this.provider = provider;
            return this;
        }

        public Builder providerId(String providerId) {
            this.providerId = providerId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder emailVerified(String emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public AuthUser build() {
            return new AuthUser(userName, email, imageUrl, provider, providerId, password, role, emailVerified);
        }
    }
*/


}
