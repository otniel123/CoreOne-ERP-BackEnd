package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.factory.CategoriaProdutoFactory;
import com.CoreOne.Erp.cadastroBase.model.CategoriaProdutoModel;
import com.CoreOne.Erp.cadastroBase.repository.CategoriaProdutoRepository;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<CategoriaProdutoResponse> listarCategoriaProduto(){
        List<CategoriaProdutoModel> categoriaProdutoModelList =
                categoriaProdutoRepository.findAll();

        List<CategoriaProdutoResponse> categoriaProdutoResponseList =
                categoriaProdutoModelList.stream().map(categoriaProdutoModel -> categoriaProdutoFactory.responseFromModel(categoriaProdutoModel)).toList();

        return categoriaProdutoResponseList;
    }

    public CategoriaProdutoResponse listarCategoriaProdutoPorId(Long id){
        Optional<CategoriaProdutoModel> categoriaProdutoModelOptional = categoriaProdutoRepository.findById(id);

        return categoriaProdutoModelOptional.map(categoriaProdutoModel -> categoriaProdutoFactory
                .responseFromModel(categoriaProdutoModel)).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }
}
