package com.amandaramos.dto;

import com.amandaramos.entity.Transacoes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class ClientesBancoDTO {


    @JsonIgnore
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String pais;
    private String telefone;
    private String usuarioResponsavel;

    @JsonIgnore
    private List transacoes;

    private Double saldoConta;

    public ClientesBancoDTO() {
    }

    public ClientesBancoDTO(Long id, String nome, String cpf, LocalDate dataNascimento, String email, String pais, String telefone, String usuarioResponsavel, List transacoes, Double saldoConta) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.pais = pais;
        this.telefone = telefone;
        this.usuarioResponsavel = usuarioResponsavel;
        this.transacoes = transacoes;
        this.saldoConta = saldoConta;

    }



    public Double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(Double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public List getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List transacoes) {
        this.transacoes = transacoes;
    }


}


