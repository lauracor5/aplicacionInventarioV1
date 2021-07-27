package com.prueba.springboot.app.inventario.models.repository;

import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
