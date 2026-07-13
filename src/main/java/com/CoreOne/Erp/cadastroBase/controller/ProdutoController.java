package com.CoreOne.Erp.cadastroBase.controller;

import com.CoreOne.Erp.cadastroBase.dto.response.ProdutoResponse;
import com.CoreOne.Erp.cadastroBase.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<ProdutoResponse>> listarProdutos(){
        return ResponseEntity.status(200).body(this.produtoService.listarProdutos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> listarProdutoPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(this.produtoService.listarProdutoPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarFornecedor(@PathVariable("id") Long id){

    }

}
