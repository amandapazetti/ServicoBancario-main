package com.amandaramos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@EntityListeners(AuditingEntityListener.class)
@Entity
public class ClientesBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String pais;
    private String telefone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;
    private String usuarioResponsavel;

    @JsonIgnore
    @OneToMany(mappedBy = "clientesBanco", cascade = CascadeType.ALL)
    private List<Transacoes> transacoes;

    @Embedded
    private DadosFinanceiros dadosFinanceiros;

    public ClientesBanco() {
    }

    public ClientesBanco(Long id, String nome, String cpf, LocalDate dataNascimento, String email, String pais, String telefone, String usuarioResponsavel) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.pais = pais;
        this.telefone = telefone;
        this.usuarioResponsavel = usuarioResponsavel;
    }


    public Double getSaldoConta() {
        return (this.dadosFinanceiros != null) ? this.dadosFinanceiros.getSaldoConta() : null;
    }

    public void setSaldoConta(Double saldoConta) {
        if (this.dadosFinanceiros == null) {
            this.dadosFinanceiros = new DadosFinanceiros();
        }
        this.dadosFinanceiros.setSaldoConta(saldoConta);
    }


    @PrePersist
    public void onPrePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }


    public void addTransacoes(Transacoes transacao) {

        if (this.transacoes == null) {
            this.transacoes = new ArrayList<>();
        }
        this.transacoes.add(transacao);
        transacao.setClientesBanco(this);
    }


    public ClientesBanco(long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;

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


    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public List<Transacoes> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacoes> transacoes) {
        this.transacoes = transacoes;
    }

    public DadosFinanceiros getDadosFinanceiros() {
        return dadosFinanceiros;
    }

    public void setDadosFinanceiros(DadosFinanceiros dadosFinanceiros) {
        this.dadosFinanceiros = dadosFinanceiros;
    }
}





