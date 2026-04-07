package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.factory.FornecedorFactory;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import com.CoreOne.Erp.cadastroBase.repository.FornecedorRepository;
import com.CoreOne.Erp.exception.EditRecordException;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    FornecedorFactory fornecedorFactory;

    public FornecedorResponse cadastrarFornecedor(FornecedorRequest fornecedorRequest){
        FornecedorModel fornecedorSalvo = this.fornecedorRepository.save(fornecedorFactory.modelFromRequest(fornecedorRequest));
        return this.fornecedorFactory.responseFromModel(fornecedorSalvo);
    }

    public FornecedorResponse listarFornecedorPorId(Long id){
        Optional<FornecedorModel> fornecedorOptional = this.fornecedorRepository.findById(id);

        return fornecedorOptional.map(fornecedor -> this.fornecedorFactory.responseFromModel(fornecedor)).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
    }

    public List<FornecedorResponse> listarFornecedor(Integer page, Integer size){
        List<FornecedorModel> fornecedorModelList =
                fornecedorRepository.findAll(PageRequest.of(page, size)).stream().toList();
        List<FornecedorResponse> fornecedorResponseList = new ArrayList<>();

        for (FornecedorModel f : fornecedorModelList){
            fornecedorResponseList.add(fornecedorFactory.responseFromModel(f));
        }
        return fornecedorResponseList;
    }

    public FornecedorResponse editarFornecedor(FornecedorRequest fornecedorRequest, Long id) throws Exception {
        FornecedorModel fornecedorAntigo = fornecedorFactory.modelFromResponse(this.listarFornecedorPorId(id));
        if (fornecedorAntigo == null){
            throw new EntityNotFoundException("Supplier not found");
        }
        FornecedorModel fornecedorAtualizado = fornecedorFactory.modelFromRequest(fornecedorRequest);

        fornecedorAtualizado.setId(id);

        fornecedorRepository.save(fornecedorAtualizado);

        return fornecedorFactory.responseFromModel(fornecedorAtualizado);
    }

    public void deletarFornecedor(Long id){
        FornecedorModel fornecedorModel = fornecedorFactory.modelFromResponse(this.listarFornecedorPorId(id));
        fornecedorRepository.delete(fornecedorModel);
    }
}
