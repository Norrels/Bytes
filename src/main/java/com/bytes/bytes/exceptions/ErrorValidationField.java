package com.bytes.bytes.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "ErrorValidationField", ref = "ErrorValidationField" , description = "Representa a estrutura de um erro de validação de campo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidationField {
    String field;
    String message;
};
