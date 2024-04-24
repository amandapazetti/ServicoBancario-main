package com.amandaramos.service.criteria;

import com.amandaramos.entity.Transacoes;
import com.amandaramos.repository.TransacoesRepository;
import com.amandaramos.service.criteria.TransacoesCriteriaServiceInterface;
import com.amandaramos.specification.TransacoesCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class TransacoesCriteriaServiceImpl implements TransacoesCriteriaServiceInterface {


    private final TransacoesRepository transacoesRepository;

    public TransacoesCriteriaServiceImpl(TransacoesRepository transacoesRepository) {
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
