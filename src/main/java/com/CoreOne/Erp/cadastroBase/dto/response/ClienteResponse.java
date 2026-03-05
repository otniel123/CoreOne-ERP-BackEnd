package com.CoreOne.Erp.cadastroBase.dto.response;

import com.CoreOne.Erp.cadastroBase.enums.TipoPessoa;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record ClienteResponse(Long id, String razaoSocial, TipoPessoa tipoPessoa,
                              String documento, String telefone, String endereco, String email) {
    public static ClienteResponse from(ClienteModel clienteModel){
        return new ClienteResponse(clienteModel.getId(), clienteModel.getRazaoSocial(),
                clienteModel.getTipoPessoa(), clienteModel.getDocumento(),
                clienteModel.getTelefone(), clienteModel.getEndereco(), clienteModel.getEmail());
    }

    public static List<ClienteResponse> fromList(List<ClienteModel> lista){
        List<ClienteResponse> listaResponse = new ArrayList<>();

        for (ClienteModel c : lista){
            ClienteResponse clienteResponse = ClienteResponse.from(c);
            listaResponse.add(clienteResponse);
        }

        return listaResponse;
    }
}
