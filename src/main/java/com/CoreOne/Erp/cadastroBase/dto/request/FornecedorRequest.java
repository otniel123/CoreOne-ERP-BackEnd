package com.CoreOne.Erp.cadastroBase.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorRequest(
        @NotBlank(message = "Informe a razão social")
        String razaoSocial,

        @NotBlank(message = "CNPJ do fornecedor não pode ser vazio")
        String cnpj,

        @NotBlank(message = "E-mail deve estar preenchido")
        @Email(message = "Email deve ser válido para cadastrar o fornecedor")
        String email,

        String telefone,

        String endereco
) {


}
