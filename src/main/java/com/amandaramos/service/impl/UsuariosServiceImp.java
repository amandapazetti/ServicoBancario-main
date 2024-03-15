package com.amandaramos.service.impl;

import com.amandaramos.dto.UsuariosDTO;
import com.amandaramos.entity.Usuarios;
import com.amandaramos.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    public List<UsuariosDTO> buscarTodosUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UsuariosDTO atualizarUsuario(Long id, UsuariosDTO usuariosDTOAtualizado) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado com ID: " + id));
        // Atualize os campos do usuário com os dados do DTO atualizado
        usuarios.setUsername(usuariosDTOAtualizado.getUsername());
        usuarios.setSenha(usuariosDTOAtualizado.getSenha());
        Usuarios updatedUsuario = usuariosRepository.save(usuarios);
        return convertToDTO(updatedUsuario);
    }

    public Optional<UsuariosDTO> buscarUsuarioPorId(Long id) {
        Optional<Usuarios> usuarioOptional = usuariosRepository.findAllById(id);
        return usuarioOptional.map(this::convertToDTO);
    }

    public boolean deletarUsuarioPorId(Long id) {

        usuariosRepository.deleteById(id);
        return false;
    }

    private UsuariosDTO convertToDTO(Usuarios usuario) {
        return new UsuariosDTO(usuario.getId(), usuario.getUsername(), usuario.getSenha());

    }


}
