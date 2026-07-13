package com.CoreOne.Erp.cadastroBase.factory;

import com.CoreOne.Erp.cadastroBase.dto.response.ProdutoResponse;
import com.CoreOne.Erp.cadastroBase.model.CategoriaProdutoModel;
import com.CoreOne.Erp.cadastroBase.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFactory {
    public ProdutoResponse responseFromModel(ProdutoModel produtoModel){
        ProdutoResponse produtoResponse = new ProdutoResponse(produtoModel.getId(),
                produtoModel.getNome(), produtoModel.getSku(), produtoModel.getDescricao(),
                produtoModel.getUnidadeMedida(), produtoModel.getPrecoCusto(), produtoModel.getPrecoVenda(),
                produtoModel.getCategoriaProdutoModel().getId());
        return produtoResponse;
    }

    public ProdutoModel modelFromResponse(ProdutoResponse produtoResponse,
                                          CategoriaProdutoModel categoriaProdutoModel){
        ProdutoModel produtoModel = new ProdutoModel(produtoResponse.id(), produtoResponse.nome()
                , produtoResponse.sku(), produtoResponse.descricao(),
                produtoResponse.unidadeMedida(), produtoResponse.precoCusto(),
                produtoResponse.precoVenda(), categoriaProdutoModel);
        return produtoModel;
    }
}
