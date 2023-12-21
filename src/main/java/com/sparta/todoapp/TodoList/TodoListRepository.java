package com.sparta.todoapp.TodoList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByOrderByUserUsernameAscCreatedAtDesc();
}

