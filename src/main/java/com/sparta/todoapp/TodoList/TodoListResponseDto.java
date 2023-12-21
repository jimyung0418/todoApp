package com.sparta.todoapp.TodoList;

import com.sparta.todoapp.CommonResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoListResponseDto extends CommonResponseDto {
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public TodoListResponseDto(TodoList savedTodoList) {
        this.title = savedTodoList.getTitle();
        this.username = savedTodoList.getUser().getUsername();
        this.content = savedTodoList.getContent();
        this.createdAt = savedTodoList.getCreatedAt();
    }
}
