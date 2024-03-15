package com.amandaramos.service;

import com.amandaramos.entity.Transacoes;
import com.amandaramos.repository.TransacoesRepository;
import com.amandaramos.specification.TransacoesCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TransacoesCriteriaService {


    private final TransacoesRepository transacoesRepository;

    @Autowired
    public TransacoesCriteriaService(TransacoesRepository transacoesRepository) {
        this.transacoesRepository = transacoesRepository;
    }

    public List<Transacoes> encontrarTransacoesPorValor(double valor) {
        Specification<Transacoes> spec = TransacoesCriteria.comValor(valor);
        return transacoesRepository.findAll(spec);
    }

    public List<Transacoes> encontrarTransacoesPorDescricao(String descricao) {
        Specification<Transacoes> spec = TransacoesCriteria.comDescricao(descricao);
        return transacoesRepository.findAll(spec);
    }

    public List<Transacoes> encontrarTransacoesPorClienteNome(String nomeCliente) {
        Specification<Transacoes> spec = TransacoesCriteria.porClienteNome(nomeCliente);
        return transacoesRepository.findAll(spec);
    }













}
