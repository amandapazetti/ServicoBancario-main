package controller;

import com.amandaramos.controller.UsuariosController;
import com.amandaramos.dto.UsuariosDTO;
import com.amandaramos.service.impl.UsuarioServiceInterface;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosControllerTest {

    @InjectMocks
    private UsuariosController usuariosController;
    @Mock
    private UsuarioServiceInterface usuariosService;

    @BeforeEach
    public void setUp() {
        usuariosService = mock(UsuarioServiceInterface.class);
        usuariosController = new UsuariosController(usuariosService);
    }
    @Test
    public void testCriarUsuario() {
        UsuariosDTO usuarioDTO = new UsuariosDTO("username", "senha");

        // Configuração do comportamento esperado do serviço
        when(usuariosService.criarUsuario(anyString(), anyString())).thenReturn(usuarioDTO);

        // Chamada do método do controlador
        ResponseEntity<UsuariosDTO> responseEntity = usuariosController.criarUsuario(usuarioDTO);

        // Verifica se o status da resposta é 201 (CREATED)
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verifica se o corpo da resposta é o mesmo objeto de usuárioDTO
        assertEquals(usuarioDTO, responseEntity.getBody());
    }
    @Test
    public void testDeletarUsuario() {
        // Mocking
        Long id = 1L;
        when(usuariosService.deletarUsuarioPorId(id)).thenReturn(true);

        // Test
        ResponseEntity<Void> responseEntity = usuariosController.deletarUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    @Test
    public void testBuscarUsuarioPorId() {
        // Mocking
        Long id = 1L;
        UsuariosDTO usuarioDTO = new UsuariosDTO(/* dados do usuário */);
        Optional<UsuariosDTO> usuarioOptional = Optional.of(usuarioDTO);
        when(usuariosService.buscarUsuarioPorId(id)).thenReturn(usuarioOptional);

        // Test
        ResponseEntity<UsuariosDTO> responseEntity = usuariosController.buscarUsuarioPorId(id);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(usuarioDTO, responseEntity.getBody());
    }
    @Test
    public void testAtualizarUsuario() {
        // Mocking
        Long id = 1L;
        UsuariosDTO usuarioDTOAtualizado = new UsuariosDTO(/* dados do usuário atualizado */);
        when(usuariosService.atualizarUsuario(id, usuarioDTOAtualizado)).thenReturn(usuarioDTOAtualizado);

        // Test
        ResponseEntity<UsuariosDTO> responseEntity = usuariosController.atualizarUsuario(id, usuarioDTOAtualizado);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(usuarioDTOAtualizado, responseEntity.getBody());

    }

    @Test
    public void testBuscarTodosUsuarios() {
        // Mocking
        Page<UsuariosDTO> usuariosPage = mock(Page.class);

        // Configuração do mock usando any() para aceitar qualquer objeto Pageable
        when(usuariosService.buscarTodosUsuariosPaginado(any(Pageable.class))).thenReturn(usuariosPage);

        // Test
        ResponseEntity<Page<UsuariosDTO>> responseEntity = usuariosController.buscarTodosUsuariosPaginados(0, 10, "id", "asc");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuariosPage, responseEntity.getBody());
    }
}
