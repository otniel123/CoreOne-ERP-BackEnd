package com.CoreOne.Erp.cadastroBase.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.PrimitiveIterator;

@Entity
@Table(name = "produtos")
public class ProdutoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String sku;

    private String descricao;

    @Column(nullable = false)
    private String unidadeMedida;

    private String precoCusto;
    private String precoVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaProdutoModel categoriaProdutoModel;

    public CategoriaProdutoModel getCategoriaProdutoModel() {
        return categoriaProdutoModel;
    }

    public void setCategoriaProdutoModel(CategoriaProdutoModel categoriaProdutoModel) {
        this.categoriaProdutoModel = categoriaProdutoModel;
    }

    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(String precoCusto) {
        this.precoCusto = precoCusto;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
