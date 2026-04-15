package com.CoreOne.Erp.cadastroBase.repository;

import com.CoreOne.Erp.cadastroBase.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
