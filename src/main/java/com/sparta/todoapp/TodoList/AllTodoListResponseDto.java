package com.sparta.todoapp.TodoList;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AllTodoListResponseDto {
    private String title;
    private String username;
    private LocalDateTime createdAt;
    private Boolean complete;

    public AllTodoListResponseDto(TodoList todoList) {
        this.title = todoList.getTitle();
        this.username = todoList.getUser().getUsername();
        this.createdAt = todoList.getCreatedAt();
        this.complete = todoList.getComplete();
    }
}
