package com.prueba.springboot.app.inventario.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;

public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {

    @Query("select m from Mercancia m where m.nombre = ?1")
    public Optional<Mercancia> findByName(String name);


}
