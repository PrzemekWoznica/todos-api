package com.example.api.todos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JUGGERNAUT")
@Data
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String description;
    private Boolean isFinished;

    public Todo(Long id, String username, String description, Boolean isFinished) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.isFinished = false;
    }

    public Todo(String username, String description) {
        this.username = username;
        this.description = description;
        this.isFinished = false;
    }
}
