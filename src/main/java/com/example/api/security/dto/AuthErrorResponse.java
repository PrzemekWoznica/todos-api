package com.example.api.security.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorResponse {
    private String errorMessage;
}
