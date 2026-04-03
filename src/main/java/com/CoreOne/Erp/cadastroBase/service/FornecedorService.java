package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.factory.FornecedorFactory;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import com.CoreOne.Erp.cadastroBase.repository.FornecedorRepository;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<FornecedorResponse> listarFornecedor(){
        List<FornecedorModel> fornecedorModelList = fornecedorRepository.findAll();
        List<FornecedorResponse> fornecedorResponseList = new ArrayList<>();

        for (FornecedorModel f : fornecedorModelList){
            fornecedorResponseList.add(fornecedorFactory.responseFromModel(f));
        }
        return fornecedorResponseList;
    }
}
