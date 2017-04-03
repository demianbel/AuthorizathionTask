package by.demianbel.exceptionHandlers;

import by.demianbel.DBService.domain.ExceptionJSONInfo;
import by.demianbel.exceptions.DataNotMatchException;
import by.demianbel.exceptions.UserNotFoundException;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataNotMatchException.class)
    protected ResponseEntity<Object> handleDataNotMatch(RuntimeException ex, WebRequest request) {
        Gson gson = new Gson();
        ExceptionJSONInfo ejinfo = new ExceptionJSONInfo("400:Bad Request", ex.getClass().getSimpleName(), ex.getMessage());
        String bodyOfResponse = gson.toJson(ejinfo);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
        Gson gson = new Gson();
        ExceptionJSONInfo ejinfo = new ExceptionJSONInfo("404:Not Found", ex.getClass().getSimpleName(), ex.getMessage());
        String bodyOfResponse = gson.toJson(ejinfo);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIO(RuntimeException ex, WebRequest request) {
        Gson gson = new Gson();
        ExceptionJSONInfo ejinfo = new ExceptionJSONInfo("500:Internal Server Error", ex.getClass().getSimpleName(), ex.getMessage());
        String bodyOfResponse = gson.toJson(ejinfo);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        Gson gson = new Gson();
        ExceptionJSONInfo ejinfo = new ExceptionJSONInfo("500:Internal Server Error", ex.getClass().getSimpleName(), ex.getMessage());
        String bodyOfResponse = gson.toJson(ejinfo);
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
