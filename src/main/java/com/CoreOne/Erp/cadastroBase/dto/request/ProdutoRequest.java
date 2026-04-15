package com.CoreOne.Erp.cadastroBase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequest(
        @NotBlank(message = "Informe o nome do produto")
        String nome,

        @NotBlank(message = "Informe o SKU do produto")
        String sku,

        String descricao,

        String unidadeMedida,

        String precoCusto,

        String precoVenda,

        @NotNull(message = "É necessário informar uma categoria para o produto")
        Long idCategoria) {
}
