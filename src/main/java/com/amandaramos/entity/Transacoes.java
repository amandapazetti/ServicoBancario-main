package com.amandaramos.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;


@Entity
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("transacoes")
    private ClientesBanco clientesBanco;



    public Transacoes() {
    }

    public Transacoes(long id, String descricao, double valor) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
    }

    public void setClientesBanco(ClientesBanco clientesBanco) {
        this.clientesBanco = clientesBanco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClientesBanco getClientesBanco() {
        return clientesBanco;
    }


}

