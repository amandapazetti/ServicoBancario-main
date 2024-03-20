package controller;

import com.amandaramos.controller.TransacoesController;
import com.amandaramos.dto.TransacoesRequestDTO;
import com.amandaramos.dto.TransacoesResponseDTO;
import com.amandaramos.service.impl.TransacoesServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacoesControllerTest {

    @Mock
    private TransacoesServiceInterface transacoesService;

    @InjectMocks
    private TransacoesController transacoesController;

    @Test
    void criarTransacao() {
        // Dados de teste
        TransacoesRequestDTO requestDTO = new TransacoesRequestDTO(/* dados da transação */);
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO(/* dados da transação criada */);
        Long clienteId = 1L;

        // Comportamento esperado do serviço
        when(transacoesService.criarTransacao(eq(clienteId), any(TransacoesRequestDTO.class)))
                .thenReturn(responseDTO);

        // Chamada ao método do controlador
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.criarTransacao(clienteId, requestDTO);

        // Verificação do resultado
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    void buscarTransacaoPorId_TransacaoExistente() {
        // Dados de teste
        Long id = 1L;
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO(/* dados da transação */);

        // Comportamento esperado do serviço
        when(transacoesService.listarTransacaoPorId(id)).thenReturn(Optional.of(responseDTO));

        // Chamada ao método do controlador
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.buscarTransacaoPorId(id);

        // Verificação do resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    void buscarTransacaoPorId_TransacaoNaoExistente() {
        // Dados de teste
        Long id = 1L;

        // Comportamento esperado do serviço
        when(transacoesService.listarTransacaoPorId(id)).thenReturn(Optional.empty());

        // Chamada ao método do controlador
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.buscarTransacaoPorId(id);

        // Verificação do resultado
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void listarTodasTransacoes() {
        // Dados de teste
        Page<TransacoesResponseDTO> transacoesPage = new PageImpl<>(Arrays.asList(/* lista de transações */));

        // Comportamento esperado do serviço
        when(transacoesService.buscarTodasTransacoesPaginado(any())).thenReturn(transacoesPage);

        // Chamada ao método do controlador
        ResponseEntity<Page<TransacoesResponseDTO>> responseEntity = transacoesController.listarTodasTransacoes(0, 10, "id", "asc");

        // Verificação do resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transacoesPage, responseEntity.getBody());
    }

    @Test
    void atualizarTransacao_TransacaoExistente() {
        // Dados de teste
        Long id = 1L;
        TransacoesRequestDTO requestDTO = new TransacoesRequestDTO(/* dados da transação atualizada */);
        TransacoesResponseDTO responseDTO = new TransacoesResponseDTO(/* dados da transação atualizada */);

        // Comportamento esperado do serviço
        when(transacoesService.atualizarTransacao(eq(id), any(TransacoesRequestDTO.class))).thenReturn(responseDTO);

        // Chamada ao método do controlador
        ResponseEntity<TransacoesResponseDTO> responseEntity = transacoesController.atualizarTransacao(id, requestDTO);

        // Verificação do resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }



    @Test
    void deletarTransacao_TransacaoDeletada() {
        // Dados de teste
        Long id = 1L;

        // Comportamento esperado do serviço
        when(transacoesService.deletarTransacao(id)).thenReturn(true);

        // Chamada ao método do controlador
        ResponseEntity<Void> responseEntity = transacoesController.deletarTransacao(id);

        // Verificação do resultado
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }





}
