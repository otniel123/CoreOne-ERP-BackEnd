package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.CategoriaProdutoRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.CategoriaProdutoResponse;
import com.CoreOne.Erp.cadastroBase.factory.CategoriaProdutoFactory;
import com.CoreOne.Erp.cadastroBase.model.CategoriaProdutoModel;
import com.CoreOne.Erp.cadastroBase.repository.CategoriaProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaProdutoServiceTest {

    @Mock
    CategoriaProdutoRepository categoriaProdutoRepository;

    @Mock
    CategoriaProdutoFactory categoriaProdutoFactory;

    @InjectMocks
    CategoriaProdutoService categoriaProdutoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(categoriaProdutoFactory.modelFromRequest(any(CategoriaProdutoRequest.class))).thenAnswer(invocation ->{
            CategoriaProdutoRequest categoriaProdutoRequest = invocation.getArgument(0);

            CategoriaProdutoModel categoriaProdutoModel =
                    new CategoriaProdutoModel(categoriaProdutoRequest.nomeCategoria(),
                            categoriaProdutoRequest.descricaoCategoria());

            return categoriaProdutoModel;
        });

        when(categoriaProdutoFactory.modelFromResponse(any(CategoriaProdutoResponse.class))).thenAnswer(invocation ->{
            CategoriaProdutoResponse categoriaProdutoResponse = invocation.getArgument(0);

            CategoriaProdutoModel categoriaProdutoModel =
                    new CategoriaProdutoModel(categoriaProdutoResponse.id(),
                            categoriaProdutoResponse.nomeCategoria(), categoriaProdutoResponse.descricaoCategoria());

            return categoriaProdutoModel;
        });

        when(categoriaProdutoFactory.responseFromModel(any(CategoriaProdutoModel.class))).thenAnswer(invocation ->{
            CategoriaProdutoModel categoriaProdutoModel = invocation.getArgument(0);

            CategoriaProdutoResponse categoriaProdutoResponse =
                    new CategoriaProdutoResponse(categoriaProdutoModel.getId(),
                            categoriaProdutoModel.getNomeCategoria(), categoriaProdutoModel.getDescricaoCategoria());

            return categoriaProdutoResponse;
        });

    }



    @Test
    @DisplayName("Should register product category successfully")
    void cadastrarCategoriaProduto() {
        CategoriaProdutoRequest categoriaProdutoRequest = new CategoriaProdutoRequest("Limpeza",
                "Produtos usados para limpeza de casa");
        CategoriaProdutoModel categoriaProdutoModel =
                categoriaProdutoFactory.modelFromRequest(categoriaProdutoRequest);
        categoriaProdutoModel.setId(1L);

        when(categoriaProdutoRepository.save(any(CategoriaProdutoModel.class))).thenReturn(categoriaProdutoModel);

        CategoriaProdutoResponse categoriaProdutoResponse =
                categoriaProdutoService.cadastrarCategoriaProduto(categoriaProdutoRequest);

        assertEquals(categoriaProdutoModel.getId(), categoriaProdutoResponse.id());
        assertEquals(categoriaProdutoRequest.nomeCategoria(), categoriaProdutoResponse.nomeCategoria());
        assertEquals(categoriaProdutoRequest.descricaoCategoria(), categoriaProdutoResponse.descricaoCategoria());
        verify(categoriaProdutoRepository, times(1)).save(any(CategoriaProdutoModel.class));
    }

    @Test
    @DisplayName("Should list product categories successfully")
    void listarCategoriaProduto() {
        List<CategoriaProdutoModel> categoriaProdutoModelList = new ArrayList<>(List.of(
                new CategoriaProdutoModel(1L, "Teste 1", "Descrição teste 1"),
                new CategoriaProdutoModel(2L, "Teste 2", "Descrição teste 2"),
                new CategoriaProdutoModel(3L, "Teste 3", "Descrição teste 3")
        ));

        when(categoriaProdutoRepository.findAll()).thenReturn(categoriaProdutoModelList);

        List<CategoriaProdutoResponse> categoriaProdutoResponseList =
                categoriaProdutoService.listarCategoriaProduto();

        verify(categoriaProdutoRepository, times(1)).findAll();
        assertEquals(categoriaProdutoModelList.size(), categoriaProdutoResponseList.size());

        for (int i = 0; i < categoriaProdutoModelList.size(); i++){
            assertEquals(categoriaProdutoModelList.get(i).getId(),
                    categoriaProdutoResponseList.get(i).id());
            assertEquals(categoriaProdutoModelList.get(i).getNomeCategoria(),
                    categoriaProdutoResponseList.get(i).nomeCategoria());
            assertEquals(categoriaProdutoModelList.get(i).getDescricaoCategoria(),
                    categoriaProdutoResponseList.get(i).descricaoCategoria());
        }
    }

    @Test
    @DisplayName("Should list category by id successfully")
    void listarCategoriaProdutoPorId() {
        CategoriaProdutoModel categoriaProdutoModel = new CategoriaProdutoModel(1L, "Teste 1",
                "Descrição teste 1");
        when(categoriaProdutoRepository.findById(1L)).thenReturn(Optional.of(categoriaProdutoModel));

        CategoriaProdutoResponse categoriaProdutoResponse =
                categoriaProdutoService.listarCategoriaProdutoPorId(1L);

        assertEquals(categoriaProdutoModel.getId(), categoriaProdutoResponse.id());
        assertEquals(categoriaProdutoModel.getNomeCategoria(),
                categoriaProdutoResponse.nomeCategoria());
        assertEquals(categoriaProdutoModel.getDescricaoCategoria(), categoriaProdutoResponse.descricaoCategoria());
        verify(categoriaProdutoRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("Should edit category successfully")
    void editarCategoriaProduto() {
        CategoriaProdutoModel categoriaProdutoModel = new CategoriaProdutoModel(1L, "Teste 1",
                "Descrição teste 1");
        when(categoriaProdutoRepository.findById(1L)).thenReturn(Optional.of(categoriaProdutoModel));
        when(categoriaProdutoRepository.save(any())).thenReturn(categoriaProdutoModel);

        CategoriaProdutoRequest categoriaProdutoRequest = new CategoriaProdutoRequest("Teste 2",
                "Descrição teste 2");

        CategoriaProdutoResponse categoriaProdutoResponse =
                categoriaProdutoService.editarCategoriaProduto(1L, categoriaProdutoRequest);

        assertEquals(categoriaProdutoRequest.nomeCategoria(), categoriaProdutoResponse.nomeCategoria());
        assertEquals(categoriaProdutoRequest.descricaoCategoria(), categoriaProdutoResponse.descricaoCategoria());
        verify(categoriaProdutoRepository, times(1)).save(any(CategoriaProdutoModel.class));
    }

    @Test
    @DisplayName("Should delete category successfully")
    void deletarCategoriaProduto() {
        Optional<CategoriaProdutoModel> categoriaProdutoModel = Optional.of(new CategoriaProdutoModel(1L,
                "Nome teste", "Descrição teste"));
        when(categoriaProdutoRepository.findById(1L)).thenReturn(categoriaProdutoModel);
        categoriaProdutoService.deletarCategoriaProduto(1L);

        verify(categoriaProdutoRepository, times(1)).delete(any(CategoriaProdutoModel.class));
    }
}