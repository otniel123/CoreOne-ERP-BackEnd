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
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setRazaoSocial(clienteRequest.razaoSocial());
        clienteModel.setTipoPessoa(clienteRequest.tipoPessoa());
        clienteModel.setDocumento(clienteRequest.documento());
        clienteModel.setTelefone(clienteRequest.telefone());
        clienteModel.setEndereco(clienteRequest.endereco());
        clienteModel.setEmail(clienteRequest.email());

        ClienteModel newCliente = clienteRepository.save(clienteModel);
        ClienteResponse clienteResponse = new ClienteResponse(newCliente.getRazaoSocial(),
                newCliente.getDocumento());
        return clienteResponse;
    }
}
