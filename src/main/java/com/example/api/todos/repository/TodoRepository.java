package com.example.api.todos.repository;

import com.example.api.todos.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUsername(String username);
    Optional<Todo> findByUsernameAndId(String username, Long id);
}
