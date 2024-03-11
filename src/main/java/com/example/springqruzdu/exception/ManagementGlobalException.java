package com.example.springqruzdu.exception;

import com.example.springqruzdu.service.TranslationRepoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ManagementGlobalException {

    private final TranslationRepoService translationRepoService;

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex, HttpServletRequest request) {


        ex.printStackTrace();
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .code("Not Found")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(translationRepoService.findByKey(List.of(ex.getMessage())))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                         HttpServletRequest request) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        System.out.println(errors);
        var build = ErrorResponse.builder()
                .code(exception.getFieldError().getCode())
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(translationRepoService.findByKey(errors))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(build);
    }


}
