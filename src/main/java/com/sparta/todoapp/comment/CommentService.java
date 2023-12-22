package com.sparta.todoapp.comment;

import com.sparta.todoapp.CommonResponseDto;
import com.sparta.todoapp.TodoList.TodoList;
import com.sparta.todoapp.TodoList.TodoListRepository;
import com.sparta.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoListRepository todoListRepository;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 할일카드가 없습니다.")
        );

        if (commentRequestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력하세요.");
        }

        Comment comment = new Comment(todoList, commentRequestDto, userDetails);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
