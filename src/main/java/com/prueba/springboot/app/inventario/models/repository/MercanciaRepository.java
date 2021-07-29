package com.prueba.springboot.app.inventario.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MercanciaRepository extends PagingAndSortingRepository<Mercancia, Long> {

    @Query("select m from Mercancia m where m.nombre = ?1")
    public Optional<Mercancia> findByName(String name);


}
