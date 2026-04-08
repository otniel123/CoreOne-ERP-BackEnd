package com.CoreOne.Erp.cadastroBase.controller;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categoria_produto")
@RestController
public class CategoriaProdutoController {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProdutoResponse> cadastrarCategoriaProduto(@RequestBody CategoriaProdutoRequest categoriaProdutoRequest){
        return ResponseEntity.status(200).body(categoriaProdutoService.cadastrarCategoriaProduto(categoriaProdutoRequest));
    }
}
