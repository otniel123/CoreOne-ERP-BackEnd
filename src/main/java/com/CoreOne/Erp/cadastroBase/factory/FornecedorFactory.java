package com.CoreOne.Erp.cadastroBase.factory;

import com.CoreOne.Erp.cadastroBase.dto.request.FornecedorRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.FornecedorResponse;
import com.CoreOne.Erp.cadastroBase.model.FornecedorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class FornecedorFactory {

    public FornecedorModel modelFromRequest(FornecedorRequest fornecedorRequest){
        FornecedorModel fornecedorModel = new FornecedorModel();

        fornecedorModel.setRazaoSocial(fornecedorRequest.razaoSocial());
        fornecedorModel.setCnpj(fornecedorRequest.cnpj());
        fornecedorModel.setEmail(fornecedorRequest.email());
        fornecedorModel.setTelefone(fornecedorRequest.telefone());
        fornecedorModel.setEndereco(fornecedorRequest.endereco());

        return fornecedorModel;
    }

    public FornecedorModel modelFromResponse(FornecedorResponse fornecedorResponse){
        FornecedorModel fornecedorModel = new FornecedorModel(fornecedorResponse.id(),
                fornecedorResponse.razaoSocial(), fornecedorResponse.cnpj(),
                fornecedorResponse.email(), fornecedorResponse.telefone(), fornecedorResponse.endereco());
        return fornecedorModel;
    }

    public FornecedorResponse responseFromModel(FornecedorModel fornecedorModel){
        FornecedorResponse fornecedorResponse = new FornecedorResponse(fornecedorModel.getId(),
                fornecedorModel.getRazaoSocial(), fornecedorModel.getCnpj(),
                fornecedorModel.getEmail(), fornecedorModel.getTelefone(),
                fornecedorModel.getEndereco());

        return fornecedorResponse;
    }
}
