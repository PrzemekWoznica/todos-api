package com.example.api.todos.dto;

import com.example.api.todos.model.Todo;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosListResponse {
    private List<Todo> todoList;
}
