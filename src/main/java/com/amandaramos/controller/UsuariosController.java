package com.amandaramos.controller;

import com.amandaramos.dto.UsuariosDTO;
import com.amandaramos.service.impl.UsuarioServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "UsuariosBancoController", description = "(Controlador para gerenciamento de  usuarios)")
public class UsuariosController {
    private UsuarioServiceInterface usuariosService;


    public UsuariosController(UsuarioServiceInterface usuariosService) {
        this.usuariosService = usuariosService;
    }

    @ApiOperation(value = "(Cria Usuario)")
    @PostMapping
    public ResponseEntity<UsuariosDTO> criarUsuario(@RequestBody UsuariosDTO usuariosDTO) {
        UsuariosDTO novoUsuario = usuariosService.criarUsuario(usuariosDTO.getUsername(), usuariosDTO.getSenha());
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "(Deleta Usuario por ID)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        boolean deletado = usuariosService.deletarUsuarioPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "(Retorna Usuario por id)")
    @GetMapping("/{id}")
    public ResponseEntity<UsuariosDTO> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<UsuariosDTO> usuario = usuariosService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "(Lista todos os  Usuarios)")
    @GetMapping
    public ResponseEntity<Page<UsuariosDTO>> buscarTodosUsuarios(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "id") String sort,
                                                                 @RequestParam(defaultValue = "asc") String order) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        } else {
            pageable = PageRequest.of(page, size);
        }
        Page<UsuariosDTO> usuariosPage = usuariosService.buscarTodosUsuariosPaginado(pageable);
        return ResponseEntity.ok(usuariosPage);
    }

    @ApiOperation(value = "(Atualiza um Usuário)")
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuariosDTO usuariosDTOAtualizado) {
        try {
            UsuariosDTO usuarioAtualizado = usuariosService.atualizarUsuario(id, usuariosDTOAtualizado);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}