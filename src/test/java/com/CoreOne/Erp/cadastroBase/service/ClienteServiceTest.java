package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.enums.TipoPessoa;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import com.CoreOne.Erp.cadastroBase.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Autowired
    @InjectMocks
    ClienteService clienteService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create individual successfully")
    void cadastrarClienteCase1() {

    }

    @Test
    @DisplayName("Should create Legal Entity customer successfully")
    void cadastrarClienteCase2() {
    }

    @Test
    void listarCliente() {
    }

    @Test
    void listarClientePorId() {
    }

    @Test
    void atualizarCliente() {
    }

    @Test
    void deletarCliente() {
    }
}