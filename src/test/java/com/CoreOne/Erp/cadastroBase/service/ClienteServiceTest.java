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
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create individual successfully")
    void cadastrarClienteCase1() {
        ClienteRequest clienteRequest = new ClienteRequest("Otniel", TipoPessoa.FISICA, "10613695950",
                "5547991986203", "Rua dos Bandeirantes, 79", "otnielmarques3@gmail.com");

        ClienteModel clienteModel = ClienteModel.from(clienteRequest);
        clienteModel.setId(1L);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(clienteModel);

        ClienteResponse clienteResponse = clienteService.cadastrarCliente(clienteRequest);

        verify(clienteRepository, times(1)).save(any());
        assertEquals(clienteModel.getId(), clienteResponse.id());
        assertEquals(clienteRequest.razaoSocial(), clienteResponse.razaoSocial());
        assertEquals(clienteRequest.tipoPessoa(), clienteResponse.tipoPessoa());
        assertEquals(clienteRequest.documento(), clienteResponse.documento());
        assertEquals(clienteRequest.telefone(), clienteResponse.telefone());
        assertEquals(clienteRequest.endereco(), clienteResponse.endereco());
        assertEquals(clienteRequest.email(), clienteResponse.email());
    }


    @Test
    @DisplayName("Should create Legal Entity customer successfully")
    void cadastrarClienteCase2() {
        ClienteRequest clienteRequest = new ClienteRequest("Philips", TipoPessoa.JURIDICA,
                "1061369595055-00",
                "554712345678", "Rua 2 de setembro, 1234", "suporte@philips.com");
        ClienteModel clienteModel = ClienteModel.from(clienteRequest);
        clienteModel.setId(1L);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(clienteModel);

        ClienteResponse clienteResponse = clienteService.cadastrarCliente(clienteRequest);

        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
        assertEquals(clienteRequest.razaoSocial(), clienteResponse.razaoSocial());
        assertEquals(clienteRequest.tipoPessoa(), clienteResponse.tipoPessoa());
        assertEquals(clienteRequest.documento(), clienteResponse.documento());
        assertEquals(clienteRequest.telefone(), clienteResponse.telefone());
        assertEquals(clienteRequest.endereco(), clienteResponse.endereco());
        assertEquals(clienteRequest.email(), clienteResponse.email());
        assertEquals(clienteModel.getId(), clienteResponse.id());

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