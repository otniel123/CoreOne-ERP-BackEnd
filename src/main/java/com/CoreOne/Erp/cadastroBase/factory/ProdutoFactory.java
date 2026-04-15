package com.CoreOne.Erp.cadastroBase.factory;

import com.CoreOne.Erp.cadastroBase.dto.response.ProdutoResponse;
import com.CoreOne.Erp.cadastroBase.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFactory {
    public ProdutoResponse responseFromModel(ProdutoModel produtoModel){
        ProdutoResponse produtoResponse = new ProdutoResponse(produtoModel.getId(),
                produtoModel.getNome(), produtoModel.getSku(), produtoModel.getDescricao(),
                produtoModel.getCategoriaProdutoModel().getId());
        return produtoResponse;
    }
}
