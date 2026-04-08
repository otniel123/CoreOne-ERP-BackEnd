package com.CoreOne.Erp.cadastroBase.controller;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categoria_produto")
@RestController
public class CategoriaProdutoController {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProdutoResponse> cadastrarCategoriaProduto(@RequestBody CategoriaProdutoRequest categoriaProdutoRequest){
        return ResponseEntity.status(200).body(categoriaProdutoService.cadastrarCategoriaProduto(categoriaProdutoRequest));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoResponse>> listarCategoriaProduto(){
        return ResponseEntity.status(200).body(categoriaProdutoService.listarCategoriaProduto());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaProdutoResponse> listarCategoriaProdutoPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(categoriaProdutoService.listarCategoriaProdutoPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaProdutoResponse> editarCategoriaProduto(@PathVariable("id") Long id, @RequestBody CategoriaProdutoRequest categoriaProdutoRequest){
        return ResponseEntity.status(200).body(categoriaProdutoService.editarCategoriaProduto(id,
                categoriaProdutoRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarCategoriaProduto(@PathVariable("id") Long id){
        categoriaProdutoService.deletarCategoriaProduto(id);
        return ResponseEntity.status(200).body(null);
    }
}
