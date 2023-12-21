package com.sparta.todoapp.TodoList;

import com.sparta.todoapp.TimeStamped;
import com.sparta.todoapp.User.User;
import com.sparta.todoapp.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TodoList extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TodoList(TodoListRequestDto todoListRequestDto, UserDetailsImpl userDetails) {
        this.title = todoListRequestDto.getTitle();
        this.content = todoListRequestDto.getContent();
        this.user = userDetails.getUser();
    }
}
