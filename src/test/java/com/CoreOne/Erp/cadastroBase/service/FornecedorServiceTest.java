package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.factory.FornecedorFactory;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import com.CoreOne.Erp.cadastroBase.repository.FornecedorRepository;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        when(fornecedorFactory.modelFromResponse(any(FornecedorResponse.class))).thenAnswer(invocation -> {
            FornecedorResponse fornecedorResponse = invocation.getArgument(0);

            FornecedorModel fornecedorModel= new FornecedorModel(fornecedorResponse.id(),
                    fornecedorResponse.razaoSocial(), fornecedorResponse.cnpj(),
                    fornecedorResponse.email(), fornecedorResponse.telefone(),
                    fornecedorResponse.endereco());

            return fornecedorModel;
        });
    }

    @Test
    @DisplayName("Should register supplier successfully")
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
    @DisplayName("Should list supplier by Id successfully")
    void listarFornecedorPorId() {
        Long id = 1L;
        FornecedorModel fornecedorModel = new FornecedorModel(id,"Fornecedor teste",
                "888999776665511", "fornecedor@gmail.com", "141458189210", "Rua de teste 1234");

        when(fornecedorRepository.findById(id)).thenReturn(Optional.of(fornecedorModel));

        FornecedorResponse fornecedorResponse = fornecedorService.listarFornecedorPorId(id);

        assertEquals(fornecedorModel.getId(), fornecedorResponse.id());
        assertEquals(fornecedorModel.getRazaoSocial(), fornecedorResponse.razaoSocial());
        assertEquals(fornecedorModel.getCnpj(), fornecedorResponse.cnpj());
        assertEquals(fornecedorModel.getEmail(), fornecedorResponse.email());
        assertEquals(fornecedorModel.getTelefone(), fornecedorResponse.telefone());
        assertEquals(fornecedorModel.getEndereco(), fornecedorResponse.endereco());
    }

    @Test
    @DisplayName("Should list suppliers successfully")
    void listarFornecedor() {
        Integer page = 0;
        Integer size = 5;

        ArrayList<FornecedorModel> fornecedorModelArrayList = new ArrayList<>(List.of(
                new FornecedorModel(1L,"Fornecedor teste 1",
                        "888999776665511 - 1", "fornecedor@gmail.com1", "141458189210-1", "Rua de" +
                        " teste 1234 - 1"),
                new FornecedorModel(2L,"Fornecedor teste 2",
                        "888999776665511 - 2", "fornecedor@gmail.com2", "141458189210-2", "Rua de" +
                        " teste 1234 - 2"),
                new FornecedorModel(3L,"Fornecedor teste 3",
                        "888999776665511 - 3", "fornecedor@gmail.com3", "141458189210-3", "Rua de" +
                        " teste 1234 - 3")
        ));

        when(fornecedorRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(fornecedorModelArrayList));

        List<FornecedorResponse> fornecedorResponseList = fornecedorService.listarFornecedor(page
                , size);

        assertEquals(fornecedorModelArrayList.size(), fornecedorResponseList.size());

    }

    @Test
    @DisplayName("Should edit supplier successfully")
    void editarFornecedorCase1() throws Exception {
        FornecedorRequest fornecedorRequest = new FornecedorRequest("Fornecedor teste atualizado",
                "123456789", "fornecedorAtualizado@gmail.com", "123456789",
                "Rua de teste atualizado 1234");

        FornecedorModel fornecedorModelAntigo = new FornecedorModel(1L,"Fornecedor teste",
                "888999776665511", "fornecedor@gmail.com", "141458189210", "Rua de teste 1234");

        FornecedorModel fornecedorModelAtualizado =
                fornecedorFactory.modelFromRequest(fornecedorRequest);

        Long id = fornecedorModelAntigo.getId();

        fornecedorModelAtualizado.setId(id);

        when(fornecedorRepository.findById(id)).thenReturn(Optional.of(fornecedorModelAntigo));
        when(fornecedorRepository.save(fornecedorModelAtualizado)).thenReturn(fornecedorModelAtualizado);

        FornecedorResponse fornecedorResponse =
                fornecedorService.editarFornecedor(fornecedorRequest, id
                );

        assertEquals(fornecedorModelAntigo.getId(), fornecedorResponse.id());
        assertNotEquals(fornecedorModelAntigo.getRazaoSocial(), fornecedorResponse.razaoSocial());
        assertNotEquals(fornecedorModelAntigo.getCnpj(), fornecedorResponse.cnpj());
        assertNotEquals(fornecedorModelAntigo.getEmail(), fornecedorResponse.email());
        assertNotEquals(fornecedorModelAntigo.getTelefone(), fornecedorResponse.telefone());
        assertNotEquals(fornecedorModelAntigo.getEndereco(), fornecedorResponse.endereco());
        verify(fornecedorRepository, times(1)).save(any(FornecedorModel.class));
        verify(fornecedorRepository, times(1)).findById(any(Long.class));

    }

    @Test
    @DisplayName("Should not edit supplier")
    void editarFornecedorCase2() throws Exception {
        FornecedorRequest fornecedorRequest = new FornecedorRequest("Fornecedor teste atualizado",
                "123456789", "fornecedorAtualizado@gmail.com", "123456789",
                "Rua de teste atualizado 1234");
        Long idInexistente = 1L;

        when(fornecedorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            fornecedorService.editarFornecedor(fornecedorRequest, idInexistente);
        });

    }

    @Test
    @DisplayName("Should delete supplier successfully")
    void deletarFornecedor() {
        FornecedorModel fornecedorModel = new FornecedorModel(1L,"Fornecedor teste",
                "888999776665511", "fornecedor@gmail.com", "141458189210", "Rua de" +
                " teste 1234");
        Long id = fornecedorModel.getId();

        when(fornecedorRepository.findById(id)).thenReturn(Optional.of(fornecedorModel));

        fornecedorService.deletarFornecedor(id);

        verify(fornecedorRepository, times(1)).delete(any(FornecedorModel.class));
    }
}