package com.CoreOne.Erp.cadastroBase.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaProdutoRequest(@NotBlank String nomeCategoria, String descricaoCategoria) {
}
