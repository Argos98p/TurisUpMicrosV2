package com.turisup.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TokenDto {

    private String token;

    /*
    public TokenDto(String token) {
        this.token = token;
    }*/
}
