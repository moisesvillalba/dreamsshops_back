package com.dailycodework.dreamsshops.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Método común para crear una respuesta de error
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }

    // Manejo específico para ProductNotFoundException
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex) {
        if (log.isDebugEnabled()) {
            log.debug("Producto no encontrado: {}", ex.getMessage());
        }

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Producto no encontrado",
                ex.getMessage()
        );
    }

    // Manejo de NoHandlerFoundException
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Recurso no encontrado",
                "La URL solicitada no corresponde a ningún recurso disponible."
        );
    }

    // Manejo general para cualquier RuntimeException no manejada
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        String errorId = UUID.randomUUID().toString(); // Generar un identificador único para el error

        if (log.isDebugEnabled()) {
            log.debug("Error interno del servidor - ID: {}: {}", errorId, ex.getMessage(), ex);
        }

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                "Se produjo un error al procesar la solicitud. Por favor, intente nuevamente más tarde o contacte al soporte técnico con el ID de error: " + errorId
        );
    }

    // Manejo general para excepciones de tipo Exception (más genérico)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("Solicitud incorrecta: {}", ex.getMessage(), ex);
        }

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Solicitud incorrecta",
                "La solicitud no pudo ser procesada. Por favor, verifique los datos enviados."
        );
    }
}
