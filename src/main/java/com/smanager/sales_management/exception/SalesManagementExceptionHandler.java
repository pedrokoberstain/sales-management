package com.smanager.sales_management.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class SalesManagementExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Exceptions> errors = generateErrorMessages(ex.getBindingResult());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String messageUser = "Recurso não encontrado.";
        String messageDev = ex.toString();
        List<Exceptions> errors = List.of(new Exceptions(messageUser, messageDev));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handlerBusinessRuleException (BusinessRuleException ex, WebRequest request) {
        String messageUser = ex.getMessage();
        String messageDev = ex.getMessage();
        List<Exceptions> errors = List.of(new Exceptions(messageUser, messageDev));
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private List<Exceptions> generateErrorMessages(BindingResult bindingResult) {
        List<Exceptions> exceptions = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String messageUser = "Campo " + fieldError.getField() + ": " + fieldError.getDefaultMessage();
            String messageDev = fieldError.toString();
            exceptions.add(new Exceptions(messageUser, messageDev));
        });

        return exceptions;
    }
}