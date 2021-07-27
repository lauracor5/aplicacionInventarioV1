package com.prueba.springboot.app.inventario.Services;

import java.util.List;
import java.util.Optional;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;

public interface MercanciaService {

    public List<Mercancia>findAll();

    public Optional<Mercancia>findById(Long id);

    public Optional<Mercancia>findByName(String nombre);

    public Mercancia save(Mercancia mercancia);

    public void deleteByid(Long idMercancia, Long idUsuario);

}
