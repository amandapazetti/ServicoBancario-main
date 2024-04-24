package com.amandaramos.service.criteria;

import com.amandaramos.dto.ClientesBancoDTO;
import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.repository.ClientesBancoRepository;

import com.amandaramos.specification.ClientesBancoCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class ClienteBancoCriteriaServiceImpl implements ClienteBancoCriteriaServiceInterface {

    private final ClientesBancoRepository clientesBancoRepository;

    public ClienteBancoCriteriaServiceImpl(ClientesBancoRepository clientesBancoRepository) {
        this.clientesBancoRepository = clientesBancoRepository;
    }

    @Override
    public List<ClientesBanco> encontrarClientesPorNome(String nome) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porNome(nome);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> encontrarClientesPorCpf(String cpf) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.comCpf(cpf);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorDataNascimento(LocalDate dataNascimento) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porDataNascimento(dataNascimento);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorEmail(String email) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porEmail(email);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorPais(String pais) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porPais(pais);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorTelefone(String telefone) {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.porTelefone(telefone);
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorSaldoNegativo() {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.comSaldoNegativo();
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    @Override
    public List<ClientesBanco> buscarClientesPorSaldoPositivo() {
        Specification<ClientesBanco> spec = ClientesBancoCriteria.comSaldoPositivo();
        List<ClientesBanco> clientes = clientesBancoRepository.findAll(spec);
        return clientes;
    }

    private ClientesBancoDTO convertToDTO(ClientesBanco cliente) {
        return new ClientesBancoDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNascimento(),
                cliente.getEmail(),
                cliente.getPais(),
                cliente.getTelefone(),
                cliente.getUsuarioResponsavel(),
                cliente.getTransacoes(),
                cliente.getDadosFinanceiros().getSaldoConta()
        );
    }
}