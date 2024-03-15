package com.amandaramos.service.impl;

import com.amandaramos.dto.TransacoesRequestDTO;
import com.amandaramos.dto.TransacoesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TransacoesServiceInterface {


    Optional<TransacoesResponseDTO> listarTransacaoPorId(Long id);

    TransacoesResponseDTO criarTransacao(Long clienteId, TransacoesRequestDTO transacaoRequest);

    boolean deletarTransacao(Long id);

    TransacoesResponseDTO atualizarTransacao(Long id, TransacoesRequestDTO transacaoAtualizada);

    Page<TransacoesResponseDTO> buscarTodasTransacoesPaginado(Pageable pageable);

}
