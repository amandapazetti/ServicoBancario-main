package com.amandaramos.service.criteria;

import com.amandaramos.entity.Transacoes;
import java.util.List;

public interface TransacoesCriteriaServiceInterface {


    List<Transacoes> encontrarTransacoesPorValor(double valor);
    List<Transacoes> encontrarTransacoesPorDescricao(String descricao);
    List<Transacoes> encontrarTransacoesPorClienteNome(String nome);

}
