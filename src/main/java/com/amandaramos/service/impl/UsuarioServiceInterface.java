package com.amandaramos.service.impl;

import com.amandaramos.dto.UsuariosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceInterface {

    List<UsuariosDTO> buscarTodosUsuarios();

    Optional<UsuariosDTO> buscarUsuarioPorId(Long id);

    UsuariosDTO criarUsuario(String username, String senha);

    boolean deletarUsuarioPorId(Long id);

    UsuariosDTO atualizarUsuario(Long id,  UsuariosDTO usuariosDTOAtualizado);


    Page<UsuariosDTO> buscarTodosUsuariosPaginado(Pageable pageable);

}


