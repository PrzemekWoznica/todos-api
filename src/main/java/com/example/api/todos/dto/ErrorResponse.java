package com.example.api.todos.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorMessage;
}
