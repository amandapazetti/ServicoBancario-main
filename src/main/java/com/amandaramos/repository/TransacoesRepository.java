package com.amandaramos.repository;

import com.amandaramos.entity.Transacoes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
    List<Transacoes> findAll(Specification<Transacoes> spec);

    @Query("SELECT t.id, t.valor, t.descricao FROM Transacoes t WHERE t.id = :id")
    Object[] findTransacaoInfoById(@Param("id") Long id);
}
