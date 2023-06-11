package com.example.api.todos.dto;

import com.example.api.todos.model.Todo;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Todo todo;
}
