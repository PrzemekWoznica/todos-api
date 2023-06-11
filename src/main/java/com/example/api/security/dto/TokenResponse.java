package com.example.api.security.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String username;
    private String token;
}
