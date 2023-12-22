package com.sparta.todoapp.comment;

import com.sparta.todoapp.TimeStamped;
import com.sparta.todoapp.TodoList.TodoList;
import com.sparta.todoapp.User.User;
import com.sparta.todoapp.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    TodoList todoList;

    public Comment(TodoList todoList, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        this.content = commentRequestDto.getContent();
        this.todoList = todoList;
        this.user = userDetails.getUser();
    }
}
