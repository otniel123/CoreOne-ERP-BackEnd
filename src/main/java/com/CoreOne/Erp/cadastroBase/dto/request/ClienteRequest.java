package com.CoreOne.Erp.cadastroBase.dto.request;

import com.CoreOne.Erp.cadastroBase.enums.TipoPessoa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.SplittableRandom;

public record ClienteRequest(
        @NotEmpty(message = "Informe a razão social para fazer o cadastro do cliente.")
        String razaoSocial,

        @NotBlank(message = "Informe o tipo da pessoa para fazer o cadastro do cliente.")
        TipoPessoa tipoPessoa,

        @NotBlank
        String documento,

        @NotBlank(message = "Informe o telefone para fazer o cadastro do cliente.")
        String telefone,

        String endereco,

        @NotBlank(message = "Informe o email para fazer o cadastro do cliente.")
        @Email(message = "Email deve ser válido para cadastrar o cliente.")
        String email
    ) {
}
