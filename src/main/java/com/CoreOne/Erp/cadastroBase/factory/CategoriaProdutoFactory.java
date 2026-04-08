package com.CoreOne.Erp.cadastroBase.factory;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.model.CategoriaProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class CategoriaProdutoFactory {

    public CategoriaProdutoModel modelFromRequest(CategoriaProdutoRequest categoriaProdutoRequest){
        CategoriaProdutoModel categoriaProdutoModel =
                new CategoriaProdutoModel(categoriaProdutoRequest.nomeCategoria(),
                        categoriaProdutoRequest.descricaoCategoria());
        return categoriaProdutoModel;
    }

    public CategoriaProdutoResponse responseFromModel(CategoriaProdutoModel categoriaProdutoModel){
        CategoriaProdutoResponse categoriaProdutoResponse =
                new CategoriaProdutoResponse(categoriaProdutoModel.getId(),
                        categoriaProdutoModel.getNomeCategoria(),
                        categoriaProdutoModel.getDescricaoCategoria());

        return categoriaProdutoResponse;
    }
}
