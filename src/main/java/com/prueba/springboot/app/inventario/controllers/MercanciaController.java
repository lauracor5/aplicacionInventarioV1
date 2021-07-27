package com.prueba.springboot.app.inventario.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.exceptions.ExceptionNegocio;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.springboot.app.inventario.Services.MercanciaService;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;


@RequestMapping("/api/inventario/mercancia")
@RestController
public class MercanciaController {

    @Autowired
    private MercanciaService mercanciaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(mercanciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mercanciaService.findById(id));
    }

    @GetMapping("obtenerPorNombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok().body(mercanciaService.findByName(nombre));
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Mercancia mercancia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaService.save(mercancia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Mercancia mercancia, @PathVariable Long id) {
        Optional<Mercancia> optMercancia = mercanciaService.findById(id);
        if (optMercancia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Mercancia mercanciaBD = optMercancia.orElseThrow();
        mercanciaBD.setNombre(mercancia.getNombre());
        mercanciaBD.setCantidad(mercancia.getCantidad());
        mercanciaBD.setUsuarioModificacion(mercancia.getUsuarioModificacion());
        mercanciaBD.setFechaModificacion(mercancia.getFechaModificacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaService.save(mercanciaBD));
    }

    @DeleteMapping("/{idMercancia}")
    public ResponseEntity<?> eliminar(@PathVariable Long idMercancia, @PathVariable Long idUsuario) {
        mercanciaService.deleteByid(idMercancia, idUsuario);
        return ResponseEntity.noContent().build();
    }
}
