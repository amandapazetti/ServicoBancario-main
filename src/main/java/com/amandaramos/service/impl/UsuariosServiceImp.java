package com.amandaramos.service.impl;

import com.amandaramos.dto.UsuariosDTO;
import com.amandaramos.entity.Usuarios;
import com.amandaramos.repository.UsuariosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuariosServiceImp implements UsuarioServiceInterface {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImp(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UsuariosDTO criarUsuario(String username, String senha) {
        Usuarios usuario = new Usuarios();
        usuario.setUsername(username);
        usuario.setSenha(senha);
        Usuarios savedUsuario = usuariosRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }

    public Page<UsuariosDTO> buscarTodosUsuariosPaginado(Pageable pageable) {
        Page<Usuarios> usuariosPage = usuariosRepository.findAll(pageable);
        return usuariosPage.map(this::convertToDTO);
    }

    public UsuariosDTO atualizarUsuario(Long id, UsuariosDTO usuariosDTOAtualizado) {
        Usuarios usuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
        // Atualize os campos do usuário com os dados do DTO atualizado
        usuarios.setUsername(usuariosDTOAtualizado.getUsername());
        usuarios.setSenha(usuariosDTOAtualizado.getSenha());
        Usuarios updatedUsuario = usuariosRepository.save(usuarios);
        return convertToDTO(updatedUsuario);
    }

    public Optional<UsuariosDTO> buscarUsuarioPorId(Long id) {
        Optional<Usuarios> usuarioOptional = usuariosRepository.findById(id);
        return usuarioOptional.map(this::convertToDTO);
    }

    public boolean deletarUsuarioPorId(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true; // Retorna true se o usuário foi deletado com sucesso
        } else {
            return false; // Retorna false se o usuário não foi encontrado
        }
    }

    private UsuariosDTO convertToDTO(Usuarios usuario) {
        return new UsuariosDTO(usuario.getId(), usuario.getUsername(), usuario.getSenha());

    }


}