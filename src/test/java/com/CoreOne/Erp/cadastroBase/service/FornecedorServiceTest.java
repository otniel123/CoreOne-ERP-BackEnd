package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.factory.FornecedorFactory;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import com.CoreOne.Erp.cadastroBase.repository.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorServiceTest {

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private FornecedorFactory fornecedorFactory;

    @InjectMocks
    private FornecedorService fornecedorService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        when(fornecedorFactory.modelFromRequest(any(FornecedorRequest.class))).thenAnswer(invocation ->{
            FornecedorRequest fornecedorRequest = invocation.getArgument(0);

            FornecedorModel fornecedorModel = new FornecedorModel();
            fornecedorModel.setRazaoSocial(fornecedorRequest.razaoSocial());
            fornecedorModel.setCnpj(fornecedorRequest.cnpj());
            fornecedorModel.setEmail(fornecedorRequest.email());
            fornecedorModel.setTelefone(fornecedorRequest.telefone());
            fornecedorModel.setEndereco(fornecedorRequest.endereco());

            return fornecedorModel;
        });

        when(fornecedorFactory.responseFromModel(any(FornecedorModel.class))).thenAnswer(invocation ->{
            FornecedorModel fornecedorModel = invocation.getArgument(0);

            FornecedorResponse fornecedorResponse =
                    new FornecedorResponse(fornecedorModel.getId(),
                            fornecedorModel.getRazaoSocial(), fornecedorModel.getCnpj(),
                            fornecedorModel.getEmail(), fornecedorModel.getTelefone(),
                            fornecedorModel.getEndereco());

            return fornecedorResponse;
        });
    }

    @Test
    void cadastrarFornecedor() {
        FornecedorRequest fornecedorRequest = new FornecedorRequest("Fornecedor teste",
                "888999776665511", "fornecedor@gmail.com", "141458189210", "Rua de teste 1234");

        FornecedorModel fornecedorModel = fornecedorFactory.modelFromRequest(fornecedorRequest);

        when(fornecedorRepository.save(any(FornecedorModel.class))).thenReturn(fornecedorModel);

        FornecedorResponse fornecedorResponse =
                fornecedorService.cadastrarFornecedor(fornecedorRequest);

        verify(fornecedorRepository, times(1)).save(any());
        assertEquals(fornecedorRequest.razaoSocial(), fornecedorResponse.razaoSocial());
        assertEquals(fornecedorRequest.cnpj(), fornecedorResponse.cnpj());
        assertEquals(fornecedorRequest.email(), fornecedorResponse.email());
        assertEquals(fornecedorRequest.telefone(), fornecedorResponse.telefone());
        assertEquals(fornecedorRequest.endereco(), fornecedorResponse.endereco());

    }

    @Test
    void listarFornecedorPorId() {
    }

    @Test
    void listarFornecedor() {
    }

    @Test
    void editarFornecedor() {
    }

    @Test
    void deletarFornecedor() {
    }
}