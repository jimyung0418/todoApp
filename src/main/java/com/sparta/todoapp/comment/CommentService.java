package com.sparta.todoapp.comment;

import com.sparta.todoapp.TodoList.TodoList;
import com.sparta.todoapp.TodoList.TodoListRepository;
import com.sparta.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글이 없습니다.")
        );

        if (!Objects.equals(comment.getUser().getUserId(), userDetails.getUser().getUserId())) {
            throw new IllegalArgumentException("작성자만 수정 및 삭제 가능합니다.");
        }

        Comment updatedComment = comment.update(commentRequestDto);
        return new CommentResponseDto(updatedComment);
    }
}
