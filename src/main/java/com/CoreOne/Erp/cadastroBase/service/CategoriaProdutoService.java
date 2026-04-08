package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.factory.CategoriaProdutoFactory;
import com.CoreOne.Erp.cadastroBase.model.CategoriaProdutoModel;
import com.CoreOne.Erp.cadastroBase.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoService {

    @Autowired
    CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    CategoriaProdutoFactory categoriaProdutoFactory;

    public CategoriaProdutoResponse cadastrarCategoriaProduto(CategoriaProdutoRequest categoriaProdutoRequest){
        CategoriaProdutoModel categoriaProdutoModel =
                categoriaProdutoFactory.modelFromRequest(categoriaProdutoRequest);

        return categoriaProdutoFactory.responseFromModel(categoriaProdutoRepository.save(categoriaProdutoModel));
    }
}
