package com.CoreOne.Erp.cadastroBase.model;

import com.CoreOne.Erp.cadastroBase.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class ClienteModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String razaoSocial;

    @NotBlank(message = "Informe o tipo da pessoa para fazer o cadastro do cliente.")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false, unique = true)
    private String documento;

    @NotBlank(message = "Informe o telefone para fazer o cadastro do cliente.")
    private String telefone;

    private String endereco;

    @NotBlank(message = "Informe o email para fazer o cadastro do cliente.")
    @Email(message = "Email deve ser válido para cadastrar o cliente.")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public @NotBlank(message = "Informe o tipo da pessoa para fazer o cadastro do cliente.") TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(@NotBlank(message = "Informe o tipo da pessoa para fazer o cadastro do cliente.") TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public @NotBlank(message = "Informe o telefone para fazer o cadastro do cliente.") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "Informe o telefone para fazer o cadastro do cliente.") String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public @NotBlank(message = "Informe o email para fazer o cadastro do cliente.") @Email(message = "Email deve ser válido para cadastrar o cliente.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Informe o email para fazer o cadastro do cliente.") @Email(message = "Email deve ser válido para cadastrar o cliente.") String email) {
        this.email = email;
    }
}
