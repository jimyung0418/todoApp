package com.sparta.todoapp.TodoList;

import com.sparta.todoapp.CommonResponseDto;
import com.sparta.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;

    public TodoListResponseDto createTodoList(TodoListRequestDto todoListRequestDto, UserDetailsImpl userDetails) {

        if (todoListRequestDto.getTitle() == null) {
            throw new IllegalArgumentException("제목을 입력하세요.");
        } else if (todoListRequestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력하세요.");
        }

        TodoList todoList = new TodoList(todoListRequestDto, userDetails);
        TodoList savedTodoList = todoListRepository.save(todoList);
        return new TodoListResponseDto(savedTodoList);
    }

    public CommonResponseDto getTodoList(Long todoId) {

        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 할일카드가 없습니다.")
        );

        return new TodoListResponseDto(todoList);
    }
}
