package com.amandaramos.repository;

import com.amandaramos.entity.Usuarios;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    List<Usuarios> findAll(Specification<Usuarios> spec);

    Optional<Usuarios> findAllById(Long id);
}


