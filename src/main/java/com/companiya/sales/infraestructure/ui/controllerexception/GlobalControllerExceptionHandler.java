package com.companiya.sales.infraestructure.ui.controllerexception;

import com.companiya.sales.domain.exceptions.NoContentException;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public final class GlobalControllerExceptionHandler {

    @Getter
    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleNoContent(Exception ex) {
        logError(ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception ex) {
        logError(ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleConstraintViolation(MethodArgumentTypeMismatchException exception) {
        logError(exception);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errors", String.format("'%s' deberia ser un '%s' valido y '%s' no lo es o no tiene el formato esperado",exception.getName(), exception.getRequiredType().getSimpleName(), exception.getValue()));
        return ResponseEntity.badRequest().body(body);
    }

    private void logError(final Exception ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
    }
}