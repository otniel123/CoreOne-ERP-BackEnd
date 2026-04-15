package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.response.ProdutoResponse;
import com.CoreOne.Erp.cadastroBase.factory.ProdutoFactory;
import com.CoreOne.Erp.cadastroBase.model.ProdutoModel;
import com.CoreOne.Erp.cadastroBase.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoFactory produtoFactory;

    public List<ProdutoResponse> listarProdutos(){
        List<ProdutoModel> listProdutoModel = this.produtoRepository.findAll();
        List<ProdutoResponse> listProdutoResponse = new ArrayList<>();

        for (ProdutoModel p : listProdutoModel){
            listProdutoResponse.add(produtoFactory.responseFromModel(p));
        }

        return listProdutoResponse;
    }
}
