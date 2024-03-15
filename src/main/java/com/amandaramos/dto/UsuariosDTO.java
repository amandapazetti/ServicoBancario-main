package com.amandaramos.dto;


import lombok.Getter;
import lombok.Setter;


public class UsuariosDTO {

    private Long id;

    // Método getUsername público para acessar o username
    // @NotBlank
    private String username;

    // Método getSenha público para acessar a senha
    //@NotBlank
    private String senha;

    public UsuariosDTO() {
    }

    public UsuariosDTO(Long id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}