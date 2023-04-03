package com.gtn.todoservice.repository.custom;

import com.gtn.todoservice.entity.Todo;

import java.util.List;

public interface TodoCustomRepository {
  List<Todo> selectAllByCustom();
}
