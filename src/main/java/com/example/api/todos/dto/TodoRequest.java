package com.example.api.todos.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private String username;
    private String description;
}
