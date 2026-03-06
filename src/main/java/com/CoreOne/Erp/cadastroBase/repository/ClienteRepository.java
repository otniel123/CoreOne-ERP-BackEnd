package com.CoreOne.Erp.cadastroBase.repository;

import com.CoreOne.Erp.cadastroBase.dto.request.ClienteRequest;
import com.CoreOne.Erp.cadastroBase.dto.response.ClienteResponse;
import com.CoreOne.Erp.cadastroBase.model.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
