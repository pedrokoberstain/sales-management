package com.smanager.sales_management.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String messageUser = "Conteúdo da solicitação inválido. Por favor, verifique os dados enviados.";
        String messageDev = ex.getMessage();
        List<Exceptions> errors = List.of(new Exceptions(messageUser, messageDev));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private List<Exceptions> generateErrorMessages(BindingResult bindingResult) {
        List<Exceptions> exceptions = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msUser = fieldError.getField() + " : " + fieldError.getDefaultMessage();
            String msDev = fieldError.toString();
            exceptions.add(new Exceptions(msUser, msDev));
        });

        return exceptions;
    }
}
