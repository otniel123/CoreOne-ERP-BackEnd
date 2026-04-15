package com.CoreOne.Erp.cadastroBase.dto.response;

public record ProdutoResponse(
        Long id,
        String nome,
        String sku,
        String descricao,
        Long idCategoria) {
}
