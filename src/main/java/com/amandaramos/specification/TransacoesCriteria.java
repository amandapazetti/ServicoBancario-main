package com.amandaramos.specification;

import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.entity.Transacoes;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
/*A classe TransaçõesCriteria é uma fábrica de especificações para a entidade Transações, que
permite definir diferentes critérios de busca como métodos estáticos. Esses métodos retornam objetos
Specification, que são utilizados para realizar consultas mais complexas no repositório TransaçõesRepository.*/
public class TransacoesCriteria {

    public static Specification<Transacoes> comValor(double valor) {
        return (root, query, builder) -> builder.equal(root.get("valor"), valor);
    }

    public static Specification<Transacoes> comDescricao(String descricao) {
        return (root, query, builder) -> builder.equal(root.get("descricao"), descricao);
    }

    public static Specification<Transacoes> porNomedoCliente(String nomeCliente) {
        return (root, query, builder) -> {
            Join<Transacoes, ClientesBanco> clienteJoin = root.join("clientesBanco", JoinType.LEFT);
            return builder.equal(clienteJoin.get("nome"), nomeCliente);
        };
    }
}




