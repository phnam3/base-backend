package com.example.basebackend.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class DataAccessException extends RuntimeException {
    private HttpStatus httpStatus;
    private List<String> errors;

    public DataAccessException(String message) {
        super(message);
    }

    public static final DataAccessException notFound() {
        return notFound("Not found");
    }

    public static final DataAccessException notFound(String message, String... errs) {
        return notFound(message, Arrays.asList(errs));
    }

    public static final DataAccessException notFound(String message, List<String> errs) {
        DataAccessException dataAccessException = new DataAccessException(message);
        dataAccessException.setHttpStatus(HttpStatus.NOT_FOUND);
        dataAccessException.setErrors(errs);
        return dataAccessException;
    }

}
