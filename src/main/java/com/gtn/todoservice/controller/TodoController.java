package com.gtn.todoservice.controller;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.model.request.UpsertTodoRequest;
import com.gtn.todoservice.model.response.BaseResponse;
import com.gtn.todoservice.model.response.TodoResponse;
import com.gtn.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {

  @Autowired
  private TodoService todoService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
  public BaseResponse<TodoResponse> getTodo(@PathVariable String id){
    try {
      Todo todo = todoService.getTodoById(id);
      return convertBaseResponse(todo);
    } catch (Exception e) {
      return new BaseResponse(false, "erorr bgt", null);
    }

  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<TodoResponse> getAllTodo() {
    List<Todo> todoList = todoService.getAllTodo();
    return todoList.stream()
        .map(this::convertTodoResponse)
        .collect(Collectors.toList());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public TodoResponse upsertTodo(@RequestBody UpsertTodoRequest request) {
    Todo todo = todoService.upsertTodo(request);
    return convertTodoResponse(todo);
  }

  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
  public BaseResponse deleteTodo(@PathVariable String id){
    todoService.deleteById(id);
    return BaseResponse.builder().success(true).build();
  }

  private TodoResponse convertTodoResponse(Todo todo) {
    return TodoResponse.builder()
        .id(todo.getId())
        .text(todo.getText())
        .completed(todo.isCompleted())
        .date(new Date())
        .build();
  }

  private BaseResponse<TodoResponse> convertBaseResponse(Todo todo) {
    return new BaseResponse(true, null, TodoResponse.builder()
        .id(todo.getId())
        .text(todo.getText())
        .completed(todo.isCompleted())
        .date(new Date())
        .build());
  }
}
