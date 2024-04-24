package com.amandaramos.specification;

import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.repository.ClientesBancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*A classe ClientesBancoCriteria é uma fábrica de especificações para a entidade ClientesBanco, que
permite definir diferentes critérios de busca como métodos estáticos. Esses métodos retornam objetos
Specification, que são utilizados para realizar consultas mais complexas no repositório ClientesBancoRepository.*/
@Component
public class ClientesBancoCriteria {

    public static Specification<ClientesBanco> porNome(String nome) {
        return (root, query, builder) -> builder.equal(root.get("nome"), nome);
    }

    public static Specification<ClientesBanco> comCpf(String cpf) {
        return (root, query, builder) -> builder.equal(root.get("cpf"), cpf);
    }

    public static Specification<ClientesBanco> porDataNascimento(LocalDate dataNascimento) {
        return (root, query, builder) -> builder.equal(root.get("dataNascimento"), dataNascimento);
    }

    public static Specification<ClientesBanco> porEmail(String email) {
        return (root, query, builder) -> builder.equal(root.get("email"), email);
    }

    public static Specification<ClientesBanco> porPais(String pais) {
        return (root, query, builder) -> builder.equal(root.get("pais"), pais);
    }

    public static Specification<ClientesBanco> porTelefone(String telefone) {
        return (root, query, builder) -> builder.equal(root.get("telefone"), telefone);
    }

    public static Specification<ClientesBanco> comSaldoNegativo() {
        return (root, query, cb) -> {
            // Acessando o caminho para dadosFinanceiros e então para saldoConta
            Expression<Double> saldoContaPath = root.get("dadosFinanceiros").get("saldoConta");
            // Criando a condição para verificar se o saldoConta é menor que 0.0
            Predicate saldoNegativoPredicate = cb.lessThan(saldoContaPath, 0.0);
            return saldoNegativoPredicate;
        };
    }
    public static Specification<ClientesBanco> comSaldoPositivo() {
        return (root, query, cb) -> {
            // Acessando o caminho para dadosFinanceiros e então para saldoConta
            Expression<Double> saldoContaPath = root.get("dadosFinanceiros").get("saldoConta");
            // Criando a condição para verificar se o saldoConta é maior que 0.0
            Predicate saldoPositivoPredicate = cb.greaterThan(saldoContaPath, 0.0);
            return saldoPositivoPredicate;
        };
    }

    public static Specification<ClientesBanco> filtrarClientes(String nome, String cpf, LocalDate dataNascimento, String email, String pais, String telefone, Double dadosFinanceiros) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.conjunction();
        };
    }
}
