package com.CoreOne.Erp.cadastroBase.service;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import com.CoreOne.Erp.cadastroBase.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest){
        ClienteModel clienteModel = ClienteModel.from(clienteRequest);
        return ClienteResponse.from(clienteRepository.save(clienteModel));
    }

    public List<ClienteResponse> listarCliente( String razaoSocial, String documento,
                                                Integer page, Integer size){
        List<ClienteModel> clientes =
                clienteRepository.findAll(PageRequest.of(page, size)).stream().toList();

        List<ClienteResponse> clienteResponses = new ArrayList<>(ClienteResponse.fromList(clientes));

        List<ClienteResponse> listaFiltrada = new ArrayList<>(clienteResponses);

        if (razaoSocial != null){
            listaFiltrada =
                    listaFiltrada.stream().filter(clienteResponse -> clienteResponse.razaoSocial().equals(razaoSocial)).toList();
        }if (documento != null){
            listaFiltrada =
                    listaFiltrada.stream().filter(clienteResponse -> clienteResponse.documento().equals(documento)).toList();
        }

        return listaFiltrada;
    }

    public Optional<ClienteResponse> listarClientePorId(Long id){
        return clienteRepository.findById(id)
                .map(ClienteResponse::from);
    }

    public ClienteResponse atualizarCliente(ClienteRequest clienteRequest, Long id){
        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);

        if (clienteModel.isEmpty() == true){
            return null;
        }

        ClienteModel clienteModelAtualizado = ClienteModel.from(clienteRequest);
        clienteModelAtualizado.setId(id);

        clienteRepository.save(clienteModelAtualizado);

        ClienteResponse clienteResponse =  ClienteResponse.from(clienteModelAtualizado);

        return clienteResponse;
    }

    public void deletarCliente(Long id){
        ClienteModel clienteModel = ClienteModel.fromOptional(clienteRepository.findById(id));

        clienteRepository.delete(clienteModel);

    }

}
