package one.digitalinnovation.patterns.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
        String cpf
) {}