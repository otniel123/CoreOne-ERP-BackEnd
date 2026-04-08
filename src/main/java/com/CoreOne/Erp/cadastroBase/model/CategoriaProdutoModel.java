package com.CoreOne.Erp.cadastroBase.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

@Entity
@Table(name = "categorias_produto")
@SQLDelete(sql = "UPDATE categorias_produto set deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class CategoriaProdutoModel implements Serializable {

    public CategoriaProdutoModel() {
    }

    public CategoriaProdutoModel(String nomeCategoria, String descricaoCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    public CategoriaProdutoModel(Long id, String nomeCategoria, String descricaoCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(name = "nome_categoria", unique = true, nullable = false)
    private String nomeCategoria;

    @Column(name = "descricao_categoria")
    private String descricaoCategoria;

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
