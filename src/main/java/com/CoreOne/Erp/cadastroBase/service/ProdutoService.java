package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.response.ProdutoResponse;
import com.CoreOne.Erp.cadastroBase.factory.ProdutoFactory;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import com.CoreOne.Erp.cadastroBase.model.ProdutoModel;
import com.CoreOne.Erp.cadastroBase.repository.ProdutoRepository;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ProdutoResponse listarProdutoPorId(Long id){
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);
        return produtoModel.map(produto -> this.produtoFactory.responseFromModel(produto)).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void deletarProduto(Long id){
        FornecedorModel fornecedorModel = this.produtoFactory.responseFromModel()
    }
}
