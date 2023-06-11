package com.example.api.todos.service;

import com.example.api.security.error.IllegalUsernameUsedException;
import com.example.api.security.service.UserService;
import com.example.api.todos.error.TodoNotFoundException;
import com.example.api.todos.model.Todo;
import com.example.api.todos.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;
    private UserService userService;

    public List<Todo> findTodosWithUsername(String username){
        String authenticatedUsername = userService.getUserFromAuthentication().getUsername();
        if(username.equals(authenticatedUsername)){
            return todoRepository.findByUsername(username);
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");
        }
    }

    public Todo findTodoWithId(String username, Long id){
        String authenticatedUsername = userService.getUserFromAuthentication().getUsername();
        if(username.equals(authenticatedUsername)){
            Optional<Todo> todo = todoRepository.findByUsernameAndId(username, id);
            if (todo.isEmpty()){
                throw new TodoNotFoundException("There is no todo with id: " + id);
            } else {
                return todo.get();
            }
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");
        }
    }

    public String saveTodo(String username, String description){
        String authenticatedUsername = userService.getUserFromAuthentication().getUsername();
        if(username.equals(authenticatedUsername)){
            Todo todo = new Todo(username, description);
            Todo savedTodo = todoRepository.save(todo);
            Map<String, String> pathMap = new HashMap<>();
            pathMap.put("username", savedTodo.getUsername());
            pathMap.put("id", String.valueOf(savedTodo.getId()));
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
            String uri = uriComponentsBuilder.path("/api/todos/{username}/{id}").buildAndExpand(pathMap).toUriString();
            return uri;
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");

        }
}

    public void changeTodo(String username, Long id){
        String authenticatedUsername = userService.getUserFromAuthentication().getUsername();
        if(username.equals(authenticatedUsername)){
            Optional<Todo> todo = todoRepository.findByUsernameAndId(username, id);
            if (todo.isEmpty()){
                throw new TodoNotFoundException("There is no todo with id: " + id);
            } else {
                Todo _todo = todo.get();
                _todo.setIsFinished(!_todo.getIsFinished());
                todoRepository.save(_todo);
            }
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");
        }
    }

    public void deleteTodo(String username, Long id){
        String authenticatedUsername = userService.getUserFromAuthentication().getUsername();
        if(username.equals(authenticatedUsername)){
            Optional<Todo> todo = todoRepository.findByUsernameAndId(username, id);
            if (todo.isEmpty()){
                throw new TodoNotFoundException("There is no todo with id: " + id);
            } else {
                todoRepository.delete(todo.get());
            }
        } else {
            throw new IllegalUsernameUsedException("User " + username + " is not authorized");
        }

    }
}
