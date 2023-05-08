package com.gtn.todoservice.service;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.repository.TodoRepository;
import com.gtn.todoservice.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TodoServiceTest {

  private static final String ID = "ID";
  private static final String TEXT = "TESTING";

  private Todo todoTest;

  @InjectMocks
  private TodoServiceImpl todoService;

  @Mock
  private TodoRepository todoRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    todoTest = Todo.builder()
        .id(ID)
        .text(TEXT)
        .build();
  }

  @Test
  public void getTodoById_success() {

    Mockito.when(todoRepository.getById(ID))
        .thenReturn(todoTest);

    Todo result = todoService.getTodoById(ID);

    Assertions.assertEquals(result, todoTest);

    Mockito.verify(todoRepository, Mockito.times(1)).getById(ID);

  }


}
