package controllers;

import com.amandaramos.controller.TransacoesController;
import com.amandaramos.dto.TransacoesRequestDTO;
import com.amandaramos.dto.TransacoesResponseDTO;
import com.amandaramos.entity.Transacoes;
import com.amandaramos.service.criteria.TransacoesCriteriaServiceInterface;
import com.amandaramos.service.impl.TransacoesServiceInterface;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransacoesControllerTest {

    @Mock
    private TransacoesServiceInterface transacoesService;

    @Mock
    private TransacoesCriteriaServiceInterface transacoesCriteriaService;

    @InjectMocks
    private TransacoesController transacoesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarTransacao() {
        // Mocking
        Long clienteId = 1L;
        TransacoesRequestDTO requestDTO = new TransacoesRequestDTO();
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO();
        when(transacoesService.criarTransacao(clienteId, requestDTO)).thenReturn(responseDTO);

        // Execution
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.criarTransacao(clienteId, requestDTO);

        // Assertion
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());

    }

    @Test
    public void testBuscarTransacaoPorId() {
        // Mocking
        Long id = 1L;
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO();
        Optional<TransacoesResponseDTO> optionalResponseDTO = Optional.of(responseDTO);
        when(transacoesService.listarTransacaoPorId(id)).thenReturn(optionalResponseDTO);

        // Execution
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.buscarTransacaoPorId(id);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testBuscarTransacaoPorIdNotFound() {
        // Mocking
        Long id = 1L;
        Optional<TransacoesResponseDTO> optionalResponseDTO = Optional.empty();
        when(transacoesService.listarTransacaoPorId(id)).thenReturn(optionalResponseDTO);

        // Execution
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.buscarTransacaoPorId(id);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testListarTodasTransacoes() {
        // Mocking
        int page = 0;
        int size = 10;
        String sort = "id";
        String order = "asc";
        List<TransacoesResponseDTO> transacoesList = new ArrayList<>();
        // Adicione algumas transações simuladas para o teste
        TransacoesResponseDTO transacao1 = new TransacoesResponseDTO();
        TransacoesResponseDTO transacao2 = new TransacoesResponseDTO();
        transacoesList.add(transacao1);
        transacoesList.add(transacao2);
        Page<TransacoesResponseDTO> transacoesPage = new PageImpl<>(transacoesList);
        when(transacoesService.buscarTodasTransacoesPaginado(any(Pageable.class))).thenReturn(transacoesPage);

        // Execution
        ResponseEntity<Page<TransacoesResponseDTO>> responseEntity = transacoesController.listarTodasTransacoes(page, size, sort, order);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transacoesPage, responseEntity.getBody());
    }

    @Test
    public void testAtualizarTransacaoFound() {
        // Mocking
        Long id = 1L;
        TransacoesRequestDTO requestDTO = new TransacoesRequestDTO();
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO();
        when(transacoesService.atualizarTransacao(id, requestDTO)).thenReturn(responseDTO);

        // Execution
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.atualizarTransacao(id, requestDTO);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    public void testAtualizarTransacaoNotFound() {
        // Mocking
        Long id = 1L;
        TransacoesRequestDTO requestDTO = new TransacoesRequestDTO();
        when(transacoesService.atualizarTransacao(id, requestDTO)).thenReturn(null);

        // Execution
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.atualizarTransacao(id, requestDTO);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() == null);
    }

    @Test
    public void testDeletarTransacaoDeleted() {
        // Mocking
        Long id = 1L;
        when(transacoesService.deletarTransacao(id)).thenReturn(true);

        // Execution
        ResponseEntity<Void> responseEntity = transacoesController.deletarTransacao(id);

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletarTransacaoNotDeleted() {
        // Mocking
        Long id = 1L;
        when(transacoesService.deletarTransacao(id)).thenReturn(false);

        // Execution
        ResponseEntity<Void> responseEntity = transacoesController.deletarTransacao(id);

        // Assertion
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testEncontrarTransacoesPorValorFound() {
        // Mocking
        double valor = 100.0;
        List<Transacoes> transacoes = new ArrayList<>();
        // Adicione algumas transações simuladas para o teste
        Transacoes transacao1 = new Transacoes();
        Transacoes transacao2 = new Transacoes();
        transacoes.add(transacao1);
        transacoes.add(transacao2);
        when(transacoesCriteriaService.encontrarTransacoesPorValor(valor)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorValor(valor);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transacoes, responseEntity.getBody());
    }

    @Test
    public void testEncontrarTransacoesPorValorNotFound() {
        // Mocking
        double valor = 100.0;
        List<Transacoes> transacoes = new ArrayList<>();
        when(transacoesCriteriaService.encontrarTransacoesPorValor(valor)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorValor(valor);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testEncontrarTransacoesPorDescricaoFound() {
        // Mocking
        String descricao = "Exemplo de descrição";
        List<Transacoes> transacoes = new ArrayList<>();
        // Adicione algumas transações simuladas para o teste
        Transacoes transacao1 = new Transacoes();
        Transacoes transacao2 = new Transacoes();
        transacoes.add(transacao1);
        transacoes.add(transacao2);
        when(transacoesCriteriaService.encontrarTransacoesPorDescricao(descricao)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorDescrição(descricao);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transacoes, responseEntity.getBody());
    }

    @Test
    public void testEncontrarTransacoesPorDescricaoNotFound() {
        // Mocking
        String descricao = "Descrição inexistente";
        List<Transacoes> transacoes = new ArrayList<>();
        when(transacoesCriteriaService.encontrarTransacoesPorDescricao(descricao)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorDescrição(descricao);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    @Test
    public void testEncontrarTransacoesPorNomeDoClienteFound() {
        // Mocking
        String nome = "Exemplo de nome";
        List<Transacoes> transacoes = new ArrayList<>();
        // Adicione algumas transações simuladas para o teste
        Transacoes transacao1 = new Transacoes();
        Transacoes transacao2 = new Transacoes();
        transacoes.add(transacao1);
        transacoes.add(transacao2);
        when(transacoesCriteriaService.encontrarTransacoesPorClienteNome(nome)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorNomeDoCliente(nome);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transacoes, responseEntity.getBody());
    }

    @Test
    public void testEncontrarTransacoesPorNomeDoClienteNotFound() {
        // Mocking
        String nome = "Nome inexistente";
        List<Transacoes> transacoes = new ArrayList<>();
        when(transacoesCriteriaService.encontrarTransacoesPorClienteNome(nome)).thenReturn(transacoes);

        // Execution
        ResponseEntity<List<Transacoes>> responseEntity = transacoesController.encontrarTransacoesPorNomeDoCliente(nome);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}