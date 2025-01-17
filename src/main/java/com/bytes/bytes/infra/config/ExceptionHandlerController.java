package com.bytes.bytes.infra.config;

import com.bytes.bytes.exceptions.BusinessException;
import com.bytes.bytes.exceptions.ErrorMessageResponse;
import com.bytes.bytes.exceptions.ErrorValidationField;
import com.bytes.bytes.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationField>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErrorValidationField> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String msg = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorValidationField error = new ErrorValidationField(err.getField(), msg);
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<List<ErrorValidationField>> handleConstraintViolationException(
            ConstraintViolationException e) {
        List<ErrorValidationField> dto = new ArrayList<>();

        e.getConstraintViolations().forEach(violation -> {
            ErrorValidationField error = new ErrorValidationField(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorValidationField> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();

        if (errorMessage.contains("ProductCategory")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorValidationField(
                            "Categoria",
                            "Categoria inválida. Valores permitidos: ACOMPANHAMENTO, SOBREMESA, BEBIDA, LANCHE"
                    ));
        }

        // Para outros erros de parse de JSON
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorValidationField(
                        "body",
                        "Erro na requisição: formato JSON inválido"
                ));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessageResponse> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Void> handleAccessDenied(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageResponse("Erro interno do servidor"));
    }

}


