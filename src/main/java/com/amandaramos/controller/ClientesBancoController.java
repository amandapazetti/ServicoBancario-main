package com.amandaramos.controller;

import com.amandaramos.Utils.PageableUtils;
import com.amandaramos.dto.ClientesBancoDTO;
import com.amandaramos.entity.ClientesBanco;
import com.amandaramos.entity.Transacoes;
import com.amandaramos.service.criteria.ClienteBancoCriteriaServiceInterface;
import com.amandaramos.service.impl.ClientesBancoServiceInterface;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes-banco")
@Api(tags = "ClientesBancoController", description = "(Controlador para gerenciamento de clientes do banco)")
public class ClientesBancoController {


    private final ClientesBancoServiceInterface clientesBancoService;
    private final ClienteBancoCriteriaServiceInterface clienteBancoCriteriaService;


    public ClientesBancoController(ClientesBancoServiceInterface clientesBancoService, ClienteBancoCriteriaServiceInterface clienteBancoCriteriaService) {
        this.clientesBancoService = clientesBancoService;
        this.clienteBancoCriteriaService = clienteBancoCriteriaService;
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
    public ResponseEntity<Page<ClientesBancoDTO>> buscarTodosClientes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String order) {
        Pageable pageable = PageableUtils.buildPageable(page, size, sort, order);
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
        if (!deletado)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Listar todos os Clientes do Banco")
    @GetMapping("/buscar-por-nome")
    public ResponseEntity<List<ClientesBanco>> encontrarClientesPorNome(@RequestParam String nome) {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.encontrarClientesPorNome(nome);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes por CPF")
    @GetMapping("/buscar-por-cpf")
    public ResponseEntity<List<ClientesBanco>> encontrarClientesPorCpf(@RequestParam String cpf) {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.encontrarClientesPorCpf(cpf);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes por data de nascimento")
    @GetMapping("/buscar-por-data-nascimento")
    public ResponseEntity<List<ClientesBanco>> buscarClientesPorDataNascimento(@RequestParam String dataNascimento) {
        LocalDate data = LocalDate.parse(dataNascimento);
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorDataNascimento(data);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes por email")
    @GetMapping("/buscar-por-email")
    public ResponseEntity<List<ClientesBanco>> buscarClientesPorEmail(@RequestParam String email) {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorEmail(email);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes por país")
    @GetMapping("/buscar-por-pais")
    public ResponseEntity<List<ClientesBanco>> buscarClientesPorPais(@RequestParam String pais) {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorPais(pais);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes por telefone")
    @GetMapping("/buscar-por-telefone")
    public ResponseEntity<List<ClientesBanco>> buscarClientesPorTelefone(@RequestParam String telefone) {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorTelefone(telefone);
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes com saldo negativo")
    @GetMapping("/buscar-com-saldo-negativo")
    public ResponseEntity<List<ClientesBanco>> buscarClientesComSaldoNegativo() {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorSaldoNegativo();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @ApiOperation(value = "Buscar clientes com saldo positivo")
    @GetMapping("/buscar-com-saldo-positivo")
    public ResponseEntity<List<ClientesBanco>> buscarClientesComSaldoPositivo() {
        List<ClientesBanco> clientes = clienteBancoCriteriaService.buscarClientesPorSaldoPositivo();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }


}
