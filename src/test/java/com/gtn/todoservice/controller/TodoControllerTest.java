package com.gtn.todoservice.controller;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

  private static final String ID = "ID";
  private static final String TEXT = "TESTING";


  private Todo todoTest;

  @MockBean
  private TodoService todoService;

  @Autowired
  private MockMvc mockMvc;


  @BeforeEach
  public void setup() {
    todoTest = Todo.builder().text(TEXT).id(ID).build();
  }

  @AfterEach
  public void verify() {
    Mockito.verify(todoService, Mockito.times(1)).getTodoById(ID);
  }

  @Test
  public void getTodo_success() throws Exception {
    Mockito.when(todoService.getTodoById(ID)).thenReturn(todoTest);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/todo/" + ID)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success", equalTo(true)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", equalTo(ID)));
  }

  @Test
  public void getTodo_falseException() throws Exception {
    Mockito.when(todoService.getTodoById(ID)).thenThrow(new NullPointerException("Error"));
    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/todo/" + ID)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.success", equalTo(false)));
  }

}
