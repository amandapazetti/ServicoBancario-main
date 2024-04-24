package com.amandaramos.repository;

import com.amandaramos.entity.ClientesBanco;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientesBancoRepository extends JpaRepository<ClientesBanco, Long> {
    List<ClientesBanco> findAll(Specification<ClientesBanco> spec);




}
