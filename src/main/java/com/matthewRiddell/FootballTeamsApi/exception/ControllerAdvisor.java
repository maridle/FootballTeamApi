package com.matthewRiddell.FootballTeamsApi.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<Object> handleDateFormatException(DateFormatException dateFormatException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", dateFormatException.toString());

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FootballTeamAlreadyExistsException.class)
    public ResponseEntity<Object> handleFootballTeamAlreadyExistsException(FootballTeamAlreadyExistsException footballTeamAlreadyExistsException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.CONFLICT);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", footballTeamAlreadyExistsException.toString());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FootballTeamNotFoundException.class)
    public ResponseEntity<Object> handleFootballTeamNotFoundException(FootballTeamNotFoundException footballTeamNotFoundException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("timestamp", LocalDateTime.now());
        body.put("message", footballTeamNotFoundException.toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFootballTeamException.class)
    public ResponseEntity<Object> handleInvalidFootballTeamException(InvalidFootballTeamException invalidFootballTeamException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
        body.put("timestamp", LocalDateTime.now());

        Map<String, String> errors = invalidFootballTeamException.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getCode, (a, b) -> b, LinkedHashMap::new));
        if (!errors.isEmpty()) {
            body.put("errors: ", errors);
        }

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
