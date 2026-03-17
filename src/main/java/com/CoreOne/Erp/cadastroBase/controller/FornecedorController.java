package com.CoreOne.Erp.cadastroBase.controller;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponse> cadastrarFornecedor(@RequestBody @Valid FornecedorRequest fornecedorRequest){
        return ResponseEntity.status(200).body(this.fornecedorService.cadastrarFornecedor(fornecedorRequest));
    }
}
