package com.example.api.todos.repository;

import com.example.api.todos.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class TodoRepositoryUnitTest {
    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        Todo todo1 = new Todo("test_username_1", "test_description_1");
        todoRepository.save(todo1);
        Todo todo2 = new Todo("test_username_1", "test_description_2");
        todoRepository.save(todo2);
        Todo todo3 = new Todo("test_username_2", "test_description_3");
        todoRepository.save(todo3);
    }

    @Test
    void shouldFindAllTodosWithUsername() {
        Iterable<Todo> todosList1 = todoRepository.findByUsername("test_username_1");
        Iterable<Todo> todosList2 = todoRepository.findByUsername("test_username_2");

        assertThat(todosList1).hasSize(2);
        assertThat(todosList2).hasSize(1);
    }

    @Test
    void shouldFindTodoWithUsernameAndId() {
        Optional<Todo> resultOptional1 = todoRepository.findByUsernameAndId("test_username_1", 1L);
        Optional<Todo> resultOptional2 = todoRepository.findByUsernameAndId("test_username_1", 2L);
        Optional<Todo> resultOptional3 = todoRepository.findByUsernameAndId("test_username_2", 3L);

        assertThat(Optional.of(resultOptional1)).isNotEmpty().isInstanceOf(Optional.class);
        assertThat(Optional.of(resultOptional2)).isNotEmpty().isInstanceOf(Optional.class);
        assertThat(Optional.of(resultOptional3)).isNotEmpty().isInstanceOf(Optional.class);

    }
}