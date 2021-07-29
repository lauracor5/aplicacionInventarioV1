package com.prueba.springboot.app.inventario.models.repository;

import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
}
