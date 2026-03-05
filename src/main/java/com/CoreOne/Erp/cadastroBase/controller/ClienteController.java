package com.CoreOne.Erp.cadastroBase.controller;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import com.CoreOne.Erp.cadastroBase.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest){
        return ResponseEntity.status(201).body(clienteService.cadastrarCliente(clienteRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ClienteResponse>> buscarClientePorId(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(clienteService.listarClientePorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarCliente(
            @RequestParam (required = false) String razaoSocial,
            @RequestParam (required = false) String documento
    ){

        return ResponseEntity.status(200).body(clienteService.listarCliente(razaoSocial, documento));

    }
}
