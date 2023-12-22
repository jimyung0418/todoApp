package com.sparta.todoapp.comment;

import com.sparta.todoapp.CommonResponseDto;
import com.sparta.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.IllformedLocaleException;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> createComment(@PathVariable Long todoId,
                                                           @RequestBody CommentRequestDto commentRequestDto,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok().body(commentService.createComment(todoId, commentRequestDto, userDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> updateComment(@PathVariable Long commentId,
                                                           @RequestBody CommentRequestDto commentRequestDto,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return ResponseEntity.ok().body(commentService.updateComment(commentId, commentRequestDto, userDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment(@PathVariable Long commentId,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.deleteComment(commentId, userDetails);
            return ResponseEntity.ok().body(new CommonResponseDto("삭제 성공!", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
