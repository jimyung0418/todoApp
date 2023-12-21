package com.sparta.todoapp.TodoList;

import com.sparta.todoapp.CommonResponseDto;
import com.sparta.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-lists")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @PostMapping
    public ResponseEntity<CommonResponseDto> createTodoList(@RequestBody TodoListRequestDto todoListRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok().body(todoListService.createTodoList(todoListRequestDto, userDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> getTodoList(@PathVariable Long todoId) {
        try {
            return ResponseEntity.ok().body(todoListService.getTodoList(todoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public List<AllTodoListResponseDto> getAllTodoList() {
        return todoListService.getAllTodoList();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> updateTodoList(@PathVariable Long todoId,
                                                            @RequestBody TodoListRequestDto todoListRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok().body(todoListService.updateTodoList(todoId, todoListRequestDto, userDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/complete/{todoId}")
    public ResponseEntity<CommonResponseDto> checkComplete(@PathVariable Long todoId,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            todoListService.checkComplete(todoId, userDetails);
            return ResponseEntity.ok().body(new CommonResponseDto("할일카드 완료!", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
