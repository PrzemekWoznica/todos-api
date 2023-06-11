package com.example.api.todos.controller;

import com.example.api.todos.dto.TodoRequest;
import com.example.api.todos.dto.TodoResponse;
import com.example.api.todos.dto.TodosListResponse;
import com.example.api.todos.dto.UriResponse;
import com.example.api.todos.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @GetMapping("/")
    public ResponseEntity getHealthCheck(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/todos/{username}")
    public ResponseEntity<TodosListResponse> getTodosForUser(@PathVariable String username){
        TodosListResponse todosListResponse = new TodosListResponse(todoService.findTodosWithUsername(username));
        return new ResponseEntity<>(todosListResponse, HttpStatus.OK);
    }

    @GetMapping("/api/todos/{username}/{id}")
    public ResponseEntity<TodoResponse> getTodoWithId(@PathVariable String username, @PathVariable Long id){
        TodoResponse todoResponse = new TodoResponse(todoService.findTodoWithId(username, id));
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/api/todos")
    public ResponseEntity<UriResponse> addTodo(@RequestBody TodoRequest todoRequest){
        String uri = todoService.saveTodo(todoRequest.getUsername(), todoRequest.getDescription());
        UriResponse uriResponse = new UriResponse(uri);
        return new ResponseEntity<>(uriResponse, HttpStatus.CREATED);
    }

    @PutMapping("api/todos/{username}/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable String username, @PathVariable Long id){
        todoService.changeTodo(username, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/todos/{username}/{id}")
    public ResponseEntity<?> removeTodo(@PathVariable String username, @PathVariable Long id){
        todoService.deleteTodo(username, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
