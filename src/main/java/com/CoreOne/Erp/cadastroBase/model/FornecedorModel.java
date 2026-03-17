package com.CoreOne.Erp.cadastroBase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class FornecedorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Informe a razão social")
    private String razaoSocial;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @NotBlank(message = "E-mail deve estar preenchido")
    @Email(message = "Email deve ser válido para cadastrar o fornecedor")
    private String email;

    private String telefone;

    private String endereco;

    public FornecedorModel() {
    }

    public FornecedorModel(Long id, String razaoSocial, String cnpj, String email, String telefone, String endereco) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Informe a razão social") String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotBlank(message = "Informe a razão social") String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotBlank(message = "E-mail deve estar preenchido") @Email(message = "Email deve ser válido para cadastrar o fornecedor") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "E-mail deve estar preenchido") @Email(message = "Email deve ser válido para cadastrar o fornecedor") String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
