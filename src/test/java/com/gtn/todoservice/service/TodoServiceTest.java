package com.gtn.todoservice.service;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.model.request.UpsertTodoRequest;
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
  private static final String EDITED_TEXT = "EDITED_TEXT";


  private Todo todoTest;

  private UpsertTodoRequest editRequest;

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
    editRequest = UpsertTodoRequest.builder()
        .id(ID)
        .text(EDITED_TEXT)
        .completed(false)
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


  @Test
  public void editTest() {
    Mockito.when(todoRepository.getById(ID))
        .thenReturn(todoTest);
    todoTest.setText(editRequest.getText());
    Mockito.when(todoRepository.save(todoTest)).thenReturn(todoTest);

    Todo upsert = todoService.upsertTodo(editRequest);

    Mockito.verify(todoRepository, Mockito.times(1)).getById(ID);
    Mockito.verify(todoRepository, Mockito.times(1)).save(todoTest);

    Assertions.assertEquals(editRequest.getText(), upsert.getText());

  }


}
