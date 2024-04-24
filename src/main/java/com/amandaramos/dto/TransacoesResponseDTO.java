package com.amandaramos.dto;



public class TransacoesResponseDTO {

    private Long id;

    private double valor;

    private String descricao;


    public TransacoesResponseDTO() {
    }

    public TransacoesResponseDTO(Long id, double valor, String descricao) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;

    }

    public TransacoesResponseDTO(long id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public TransacoesResponseDTO(String descricao, double valor) {
        // TODO document why this constructor is empty
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
}