package com.CoreOne.Erp.cadastroBase.dto.response;

public record FornecedorResponse(Long id, String razaoSocial, String cnpj, String email,
                                 String telefone, String endereco) {
}
