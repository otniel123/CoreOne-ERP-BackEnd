package com.CoreOne.Erp.cadastroBase.repository;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {


}
