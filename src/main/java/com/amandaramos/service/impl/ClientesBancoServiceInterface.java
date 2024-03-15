package com.amandaramos.service.impl;

import com.amandaramos.dto.ClientesBancoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface ClientesBancoServiceInterface {


    Optional<ClientesBancoDTO> buscarClientePorId(Long id);

    ClientesBancoDTO criarCliente(String nome, String cpf, LocalDate dataNascimento, String email, String pais, String telefone, Double saldoConta);

    boolean deletarCliente(Long id);

    ClientesBancoDTO atualizarCliente(Long id, ClientesBancoDTO clienteAtualizado);

    Page<ClientesBancoDTO> buscarTodosClientesPaginado(Pageable pageable);

}
