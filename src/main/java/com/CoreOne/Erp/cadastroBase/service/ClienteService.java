package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import com.CoreOne.Erp.cadastroBase.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest){
        ClienteModel clienteModel = ClienteModel.from(clienteRequest);
        return ClienteResponse.from(clienteRepository.save(clienteModel));
    }

}
