package com.amandaramos.service.impl;

import com.amandaramos.dto.ClientesBancoDTO;
import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.entity.DadosFinanceiros;
import com.amandaramos.repository.ClientesBancoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesBancoServiceImpl implements ClientesBancoServiceInterface {


    private final ClientesBancoRepository clientesBancoRepository;

    public ClientesBancoServiceImpl(ClientesBancoRepository clientesBancoRepository) {
        this.clientesBancoRepository = clientesBancoRepository;
    }

    public ClientesBancoDTO criarCliente(String nome, String cpf, LocalDate dataNascimento, String email, String pais, String telefone, Double saldoConta) {
        ClientesBanco clientesBanco = new ClientesBanco();
        clientesBanco.setNome(nome);
        clientesBanco.setCpf(cpf);
        clientesBanco.setDataNascimento(dataNascimento);
        clientesBanco.setEmail(email);
        clientesBanco.setPais(pais);
        clientesBanco.setTelefone(telefone);

        // Configurar DadosFinanceiros se necessário
        DadosFinanceiros dados = new DadosFinanceiros();
        dados.setSaldoConta(saldoConta);
        clientesBanco.setDadosFinanceiros(dados);

        // Salvar o cliente no banco de dados
        ClientesBanco savedClienteBanco = clientesBancoRepository.save(clientesBanco);
        return convertToDTO(savedClienteBanco);
    }

    public Page<ClientesBancoDTO> buscarTodosClientesPaginado(Pageable pageable) {
        Page<ClientesBanco> clientesPage = clientesBancoRepository.findAll(pageable);
        return clientesPage.map(this::convertToDTO);
    }


    public Optional<ClientesBancoDTO> buscarClientePorId(Long id) {
        Optional<ClientesBanco> clientesBancoOptional = clientesBancoRepository.findById(id);
        return clientesBancoOptional.map(this::convertToDTO);
    }


    public ClientesBancoDTO atualizarCliente(Long id, ClientesBancoDTO clienteAtualizado) {
        ClientesBanco cliente = clientesBancoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

        if (clienteAtualizado.getNome() != null) {
            cliente.setNome(clienteAtualizado.getNome());
        }

        if (clienteAtualizado.getCpf() != null) {
            cliente.setCpf(clienteAtualizado.getCpf());
        }

        // Verifique e atualize outros atributos conforme necessário
        ClientesBanco atualizaCliente = clientesBancoRepository.save(cliente);
        return convertToDTO(atualizaCliente);

    }

    public boolean deletarCliente(Long id) {
        clientesBancoRepository.deleteById(id);
        return false;
    }

    private ClientesBancoDTO convertToDTO(ClientesBanco clientesBanco) {
        return new ClientesBancoDTO(clientesBanco.getId(), clientesBanco.getNome(), clientesBanco.getCpf(), clientesBanco.getDataNascimento(), clientesBanco.getEmail(), clientesBanco.getPais(), clientesBanco.getTelefone(), clientesBanco.getUsuarioResponsavel(), clientesBanco.getTransacoes(), clientesBanco.getDadosFinanceiros().getSaldoConta());
    }
}