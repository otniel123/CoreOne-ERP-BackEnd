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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @DisplayName("Should list customers successfully")
    void listarCliente() {
        List<ClienteModel> clienteModelList = new ArrayList<>(List.of(
                new ClienteModel(1L, "Otniel Marques",
                        TipoPessoa.FISICA,
                        "123456789",
                        "5547991986203", "Rua dos Bandeirantes, 79", "otnielmarques3@gmail.com"),
                new ClienteModel(2L, "Philips", TipoPessoa.JURIDICA, "545487614196-00",
                        "554712345678", "Rua dois de Setembro, 1234", "suporte@philips.com"),
                new ClienteModel(3L, "Bella janela", TipoPessoa.JURIDICA, "12345678912",
                        "554712345678", "Rua Goias, 1000", "suporte@bellajanela.com")
        ));
        Integer page = 0;
        Integer size = 1;
        when(clienteRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(clienteModelList));
        List<ClienteResponse> clienteResponseListCase1 = clienteService.listarCliente(null, null,
                page, size);

        assertEquals(clienteModelList.size(), clienteResponseListCase1.size());
    }

    @Test
    @DisplayName("Should list customer by id successfully")
    void listarClientePorId() {
        ClienteModel clienteModel = new ClienteModel(1L, "Teste", TipoPessoa.JURIDICA, "111.222" +
                ".333-4444", "123456789", "Rua do teste", "teste@email.com");

        when(clienteRepository.findById(clienteModel.getId())).thenReturn(Optional.of(clienteModel));
        Optional<ClienteResponse> clienteModelBanco = clienteService.listarClientePorId(clienteModel.getId());

        verify(clienteRepository, times(1)).findById(clienteModel.getId());

        assertEquals(clienteModel.getId(), clienteModelBanco.get().id());
        assertEquals(clienteModel.getRazaoSocial(), clienteModelBanco.get().razaoSocial());
        assertEquals(clienteModel.getTipoPessoa(), clienteModelBanco.get().tipoPessoa());
        assertEquals(clienteModel.getDocumento(), clienteModelBanco.get().documento());
        assertEquals(clienteModel.getTelefone(), clienteModelBanco.get().telefone());
        assertEquals(clienteModel.getEndereco(), clienteModelBanco.get().endereco());
        assertEquals(clienteModel.getEmail(), clienteModelBanco.get().email());
    }

    @Test
    @DisplayName("Should update customer successfully")
    void atualizarClienteCase1() {
        ClienteRequest clienteRequest = new ClienteRequest("Teste", TipoPessoa.JURIDICA, "111.222" +
                ".333-4444", "123456789", "Rua do teste", "teste@email.com");
        Optional<ClienteModel> clienteModel = Optional.of(ClienteModel.from(clienteRequest));
        Long id = 1L;
        clienteModel.get().setId(id);

        when(clienteRepository.findById(id)).thenReturn(clienteModel);

        ClienteResponse clienteResponse = clienteService.atualizarCliente(clienteRequest, id);

        verify(clienteRepository, times(1)).save(any(ClienteModel.class));

        assertEquals(clienteResponse.id(), clienteModel.get().getId());
        assertEquals(clienteResponse.razaoSocial(), clienteModel.get().getRazaoSocial());
        assertEquals(clienteResponse.tipoPessoa(), clienteModel.get().getTipoPessoa());
        assertEquals(clienteResponse.documento(), clienteModel.get().getDocumento());
        assertEquals(clienteResponse.telefone(), clienteModel.get().getTelefone());
        assertEquals(clienteResponse.endereco(), clienteModel.get().getEndereco());
        assertEquals(clienteResponse.email(), clienteModel.get().getEmail());
    }

    @Test
    @DisplayName("Should not update customer")
    void atualizarClienteCase2() {
        ClienteRequest clienteRequest = new ClienteRequest("Teste", TipoPessoa.JURIDICA, "111.222" +
                ".333-4444", "123456789", "Rua do teste", "teste@email.com");
        Long id = 1L;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        ClienteResponse clienteResponse = clienteService.atualizarCliente(clienteRequest,id);

        verify(clienteRepository, never()).save(any());
        assertNull(clienteResponse);
    }

    @Test
    @DisplayName("Should delete legal entity successfully")
    void deletarCliente() {
        ClienteModel clienteModel = new ClienteModel(1L, "Teste", TipoPessoa.JURIDICA, "111.222" +
                ".333-4444", "123456789", "Rua do teste", "teste@email.com");

        when(clienteRepository.findById(clienteModel.getId())).thenReturn(Optional.of(clienteModel));

        clienteService.deletarCliente(1L);

        verify(clienteRepository, times(1)).delete(any(ClienteModel.class));
    }
}