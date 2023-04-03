package com.gtn.todoservice.repository;

import com.gtn.todoservice.entity.Todo;
import com.gtn.todoservice.repository.custom.TodoCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String>, TodoCustomRepository {
  public Todo findFirstByTextAndCompleted(String text, boolean completed);
  public List<Todo> findByCompleted(boolean completed);
}
