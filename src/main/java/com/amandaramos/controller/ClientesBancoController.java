package com.amandaramos.controller;

import com.amandaramos.dto.ClientesBancoDTO;
import com.amandaramos.service.impl.ClientesBancoServiceInterface;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes-banco")
@Api(tags = "ClientesBancoController", description = "(Controlador para gerenciamento de clientes do banco)")
public class ClientesBancoController {


    private final ClientesBancoServiceInterface clientesBancoService;


    public ClientesBancoController(ClientesBancoServiceInterface clientesBancoService) {
        this.clientesBancoService = clientesBancoService;
    }

    @ApiOperation(value = "Criar um novo Cliente")
    @PostMapping
    public ResponseEntity<ClientesBancoDTO> criarClienteBanco(@RequestBody ClientesBancoDTO clienteDTO) {
        ClientesBancoDTO novoClienteBanco = clientesBancoService.criarCliente(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getDataNascimento(), clienteDTO.getEmail(), clienteDTO.getPais(), clienteDTO.getTelefone(), clienteDTO.getSaldoConta());
        return new ResponseEntity<>(novoClienteBanco, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Buscar cliente por id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientesBancoDTO> buscarClientePorId(@PathVariable Long id) {
        Optional<ClientesBancoDTO> cliente = clientesBancoService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ApiOperation(value = "(Listar todos os Clientes do Banco)")
    @GetMapping
    public ResponseEntity<Page<ClientesBancoDTO>> buscarTodosClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        } else {
            pageable = PageRequest.of(page, size);
        }
        Page<ClientesBancoDTO> clientesPage = clientesBancoService.buscarTodosClientesPaginado(pageable);
        return ResponseEntity.ok(clientesPage);
    }


    @ApiOperation(value = "Atualizar Cliente por Id")
    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClientesBancoDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClientesBancoDTO clienteAtualizado) {
        ClientesBancoDTO clienteAtualizadoDTO = clientesBancoService.atualizarCliente(id, clienteAtualizado);
        if (clienteAtualizadoDTO != null) {
            return ResponseEntity.ok(clienteAtualizadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @ApiOperation(value = "Deletar Cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        boolean deletado = clientesBancoService.deletarCliente(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}