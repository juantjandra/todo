package com.gtn.todoservice.service.impl;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.model.request.UpsertTodoRequest;
import com.gtn.todoservice.repository.TodoRepository;
import com.gtn.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
  @Autowired
  private TodoRepository todoRepository;

  @Override
  public Todo upsertTodo(UpsertTodoRequest request) {
    if (StringUtils.isEmpty(request.getId())) {
      return insert(request);
    }
    return update(request);
  }

  @Override
  public Todo getTodoById(String id) {
    return todoRepository.getById(id);
  }

  @Override
  public List<Todo> getAllTodo() {
    return todoRepository.findAll();
  }

  @Override
  public boolean deleteById(String id) {
    todoRepository.deleteById(id);
    return true;
  }


  private Todo insert(UpsertTodoRequest request) {
    Todo todo = Todo.builder()
        .text(request.getText())
        .completed(request.isCompleted())
        .build();

    return todoRepository.save(todo);
  }

  private Todo update(UpsertTodoRequest request) {
    Todo todo = getTodoById(request.getId());
    todo.setText(request.getText());
    todo.setCompleted(request.isCompleted());
    return todoRepository.save(todo);
  }
}
