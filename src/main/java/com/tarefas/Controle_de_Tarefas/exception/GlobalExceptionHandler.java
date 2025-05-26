package com.tarefas.Controle_de_Tarefas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Quando não encontrar um recurso
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroPadronizado> tratarNaoEncontrado(ResourceNotFoundException ex, HttpServletRequest request) {
        ErroPadronizado erro = new ErroPadronizado(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Quando falhar a validação de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> tratarValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Erro de validação");

        ErroPadronizado erro = new ErroPadronizado(
                LocalDateTime.now(),
                mensagem,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Outros erros genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadronizado> tratarErroGeral(Exception ex, HttpServletRequest request) {
        ErroPadronizado erro = new ErroPadronizado(
                LocalDateTime.now(),
                "Erro interno: " + ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
