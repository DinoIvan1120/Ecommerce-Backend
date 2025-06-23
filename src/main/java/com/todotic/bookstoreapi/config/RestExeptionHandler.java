package com.todotic.bookstoreapi.config;

import com.todotic.bookstoreapi.exeption.BackRequestException;
import com.todotic.bookstoreapi.exeption.ResourceNotFounExeption;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@AllArgsConstructor
@RestControllerAdvice
public class RestExeptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail MethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "La solicitud tiene errores de validación");


        Set<String> errors = new HashSet<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, Locale.getDefault());
            errors.add(message);
        }
        problemDetail.setProperty("errors", errors);

        return problemDetail;

    }

    @ExceptionHandler(ResourceNotFounExeption.class)
    public ProblemDetail handleResourceNotFoundExeption(ResourceNotFounExeption exception){
        return  ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "El recurso no ha sido encontrado");

    }

    @ExceptionHandler(BackRequestException.class)
    public ProblemDetail handleBackRequestException(BackRequestException exception){
        return  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());

    }
}
