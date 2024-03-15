package com.amandaramos.specification;

import com.amandaramos.entity.Usuarios;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*A classe UsuarioCriteria é uma fábrica de especificações para a entidade Usuario, que
permite definir diferentes critérios de busca como métodos estáticos. Esses métodos retornam objetos
Specification, que são utilizados para realizar consultas mais complexas no repositório UsuarioRepository.*/
public class UsuariosCriteria {
    public static Specification<Usuarios> porUsername(String username) {
        return (root, query, builder) -> builder.equal(root.get("username"), username);
    }

    public static Specification<Usuarios> porSenha(String senha) {
        return (root, query, builder) -> builder.equal(root.get("senha"), senha);
    }
}
