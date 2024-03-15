package com.amandaramos.controller;

import com.amandaramos.dto.TransacoesRequestDTO;
import com.amandaramos.dto.TransacoesResponseDTO;
import com.amandaramos.service.impl.TransacoesServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
@Api(tags = "TransacoesController", description = "(Controlador para gerenciamento de transacoes)")
public class TransacoesController {


    private final TransacoesServiceInterface transacoesService;

    public TransacoesController(TransacoesServiceInterface transacoesService) {
        this.transacoesService = transacoesService;
    }


    @ApiOperation(value = "Criar uma novo Transação")
    @PostMapping("/{clienteId}")
    public ResponseEntity<TransacoesResponseDTO> criarTransacao(@PathVariable Long clienteId, @Valid @RequestBody TransacoesRequestDTO transacaoRequest) {
        TransacoesResponseDTO transacaoCriada = transacoesService.criarTransacao(clienteId, transacaoRequest);
        return new ResponseEntity<>(transacaoCriada, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Buscar Transação por Id")
    @GetMapping("/{id}")
    public ResponseEntity<TransacoesResponseDTO> buscarTransacaoPorId(@PathVariable Long id) {
        Optional<TransacoesResponseDTO> transacao = transacoesService.listarTransacaoPorId(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Listar todas as Transações")
    @GetMapping
    public ResponseEntity<Page<TransacoesResponseDTO>> listarTodasTransacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<TransacoesResponseDTO> transacoesPage = transacoesService.buscarTodasTransacoesPaginado(pageable);
        return ResponseEntity.ok(transacoesPage);
    }


    @ApiOperation(value = "Atualizar Transação")
    @PutMapping("/{id}")
    public ResponseEntity<TransacoesResponseDTO> atualizarTransacao(@PathVariable Long id, @Valid @RequestBody TransacoesRequestDTO transacaoAtualizada) {
        TransacoesResponseDTO transacao = transacoesService.atualizarTransacao(id, transacaoAtualizada);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se a transação não for encontrada
        }

    }

    @ApiOperation(value = "Deletar Transação")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        boolean deletado = transacoesService.deletarTransacao(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}