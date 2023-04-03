package com.gtn.todoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(
      name="system-uuid",
      strategy ="uuid2"
  )
  private String id;
  private String text;
  private boolean completed;
}
