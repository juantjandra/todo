package com.gtn.todoservice.service;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.model.request.UpsertTodoRequest;

import java.util.List;

public interface TodoService {
  public Todo upsertTodo(UpsertTodoRequest request);
  public Todo getTodoById(String id);
  public List<Todo> getAllTodo();
  public boolean deleteById(String id);
}
