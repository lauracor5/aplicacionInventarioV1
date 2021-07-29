package com.prueba.springboot.app.inventario.Services;

import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario>findAll();

    public Page<Usuario>findAll(Pageable pageable);

    public Optional<Usuario> findByid(Long id);

    public Usuario save(Usuario usuario);

    public void deleteByid(Long id);
}
