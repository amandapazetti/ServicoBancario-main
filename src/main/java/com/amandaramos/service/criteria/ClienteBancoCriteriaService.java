package com.amandaramos.service;

import com.amandaramos.entity.ClientesBanco;

import com.amandaramos.repository.ClientesBancoRepository;

import com.amandaramos.specification.ClientesBancoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClienteBancoCriteriaService {
    private ClientesBancoRepository clientesBancoRepository;

    private final ClientesBancoCriteria clientesBancoCriteria;

    @Autowired
    public void ClientesBancoCriteriaService(ClientesBancoRepository clientesBancoRepository) {
        this.clientesBancoRepository = clientesBancoRepository;
    }

    public ClienteBancoCriteriaService(ClientesBancoRepository clientesBancoRepository, ClientesBancoCriteria clientesBancoCriteria) {
        this.clientesBancoRepository = clientesBancoRepository;
        this.clientesBancoCriteria = clientesBancoCriteria;
    }


    public List<ClientesBanco> encontrarClientesPorNome(String nome) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porNome(nome);
        return clientesBancoRepository.findAll(spec);
    }

    public List<ClientesBanco> encontrarClientesPorCpf(String cpf) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.comCpf(cpf);
        return clientesBancoRepository.findAll(spec);
    }

    public List<ClientesBanco> findByDataNascimento(LocalDate dataNascimento) {
        return clientesBancoRepository.findAll(clientesBancoCriteria.porDataNascimento(dataNascimento));

    }

    public List<ClientesBanco> findByEmail(String email) {
        return clientesBancoRepository.findAll(clientesBancoCriteria.porEmail(email));
    }

    public List<ClientesBanco> findByPais(String pais) {
        return clientesBancoRepository.findAll(clientesBancoCriteria.porPais(pais));
    }

    public List<ClientesBanco> findByTelefone(String telefone) {
        return clientesBancoRepository.findAll(clientesBancoCriteria.porTelefone(telefone));
    }



    public List<ClientesBanco> findBySaldoNegativo() {
        return clientesBancoRepository.findAll(clientesBancoCriteria.comSaldoNegativo());
    }

    public List<ClientesBanco> findBySaldoPositivo() {
        return clientesBancoRepository.findAll(clientesBancoCriteria.comSaldoPositivo());
    }

}
