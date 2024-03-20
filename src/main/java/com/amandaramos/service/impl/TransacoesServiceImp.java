package com.amandaramos.service.impl;


import com.amandaramos.dto.TransacoesRequestDTO;
import com.amandaramos.dto.TransacoesResponseDTO;
import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.entity.Transacoes;
import com.amandaramos.repository.ClientesBancoRepository;
import com.amandaramos.repository.TransacoesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacoesServiceImp implements TransacoesServiceInterface {

    private final TransacoesRepository transacoesRepository;
    private final ClientesBancoRepository clientesBancoRepository;


    public TransacoesServiceImp(TransacoesRepository transacoesRepository, ClientesBancoRepository clientesBancoRepository) {
        this.transacoesRepository = transacoesRepository;
        this.clientesBancoRepository = clientesBancoRepository;
    }

    public TransacoesResponseDTO criarTransacao(Long clienteId, TransacoesRequestDTO transacoesRequest) {
        Transacoes transacoes = new Transacoes();
        transacoes.setValor(transacoesRequest.getValor());
        transacoes.setDescricao(transacoesRequest.getDescricao());

        // Aqui você precisa obter o cliente do banco com base no ID fornecido
        ClientesBanco cliente = clientesBancoRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente do banco não encontrado com ID: " + clienteId));

        // Configurar o cliente do banco na transação
        transacoes.setClientesBanco(cliente);

        // Salvar a transação no banco de dados
        Transacoes savedTransacao = transacoesRepository.save(transacoes);

        // Converter a transação salva em DTO de resposta
        return convertToDTO(savedTransacao);
    }

    public Page<TransacoesResponseDTO> buscarTodasTransacoesPaginado(Pageable pageable) {
        Page<Transacoes> transacoesPage = transacoesRepository.findAll(pageable);
        return transacoesPage.map(this::convertToDTO);
    }

    public Optional<TransacoesResponseDTO> listarTransacaoPorId(Long id) {
        Optional<Transacoes> transacoesOptional = transacoesRepository.findById(id);
        return transacoesOptional.map(this::convertToDTO);
    }


    public boolean deletarTransacao(Long id) {
        transacoesRepository.deleteById(id);
        return false;
    }


    public TransacoesResponseDTO atualizarTransacao(Long id, TransacoesRequestDTO transacaoAtualizada) {
        Transacoes transacao = transacoesRepository.findById(id).orElseThrow(() -> new RuntimeException("Transação não encontrada com ID: " + id));
        transacao.setValor(transacaoAtualizada.getValor());
        transacao.setDescricao(transacaoAtualizada.getDescricao());
        Transacoes atualizaTransacao = transacoesRepository.save(transacao);
        return convertToDTO(atualizaTransacao);
    }

    private TransacoesResponseDTO convertToDTO(Transacoes transacoes) {
        return new TransacoesResponseDTO(transacoes.getId(), transacoes.getDescricao(), transacoes.getValor());
    }
}



