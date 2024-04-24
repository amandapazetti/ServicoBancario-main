package com.amandaramos.service.criteria;

import com.amandaramos.entity.ClientesBanco;

import java.time.LocalDate;
import java.util.List;


public interface ClienteBancoCriteriaServiceInterface {

    List<ClientesBanco> encontrarClientesPorNome(String nome);

    List<ClientesBanco> encontrarClientesPorCpf(String cpf);

    List<ClientesBanco> buscarClientesPorDataNascimento(LocalDate dataNascimento);

    List<ClientesBanco> buscarClientesPorEmail(String email);

    List<ClientesBanco> buscarClientesPorPais(String pais);

    List<ClientesBanco> buscarClientesPorTelefone(String telefone);

    List<ClientesBanco> buscarClientesPorSaldoNegativo();

    List<ClientesBanco> buscarClientesPorSaldoPositivo();

}
