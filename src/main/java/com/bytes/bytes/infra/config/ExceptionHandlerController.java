package com.bytes.bytes.infra.config;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Hidden
@ControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public record ErrorMessageDTO(String field, String message){};

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String msg = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(err.getField(), msg);
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleConstraintViolationException(
            ConstraintViolationException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getConstraintViolations().forEach(violation -> {
            ErrorMessageDTO error = new ErrorMessageDTO(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDTO> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();

        // Se for erro de enum inválido, customiza a mensagem
        if (errorMessage.contains("ProductCategory")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDTO(
                            "Categoria",
                            "Categoria inválida. Valores permitidos: ACOMPANHAMENTO, SOBREMESA, BEBIDA, LANCHE"
                    ));
        }

        // Para outros erros de parse de JSON
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDTO(
                        "body",
                        "Erro na requisição: formato JSON inválido"
                ));
    }

}


