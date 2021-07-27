package com.prueba.springboot.app.inventario.Services;

import com.prueba.springboot.app.inventario.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario>findAll();

    public Optional<Usuario> findByid(Long id);

    public Usuario save(Usuario usuario);

    public void deleteByid(Long id);
}
