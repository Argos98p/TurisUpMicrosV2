package com.turisup.resourcesservice.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    Long id;
    String email;
    String username;
    String imageUrl;
}
