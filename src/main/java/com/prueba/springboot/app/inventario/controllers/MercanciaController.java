package com.prueba.springboot.app.inventario.controllers;

import java.util.*;

import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.exceptions.ExceptionNegocio;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.prueba.springboot.app.inventario.Services.MercanciaService;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;

import javax.validation.Valid;


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

    @GetMapping("/pagina")
    public ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok().body(mercanciaService.findAll(pageable));
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
    public ResponseEntity<?> crear(@Valid  @RequestBody Mercancia mercancia, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaService.save(mercancia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Mercancia mercancia, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
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

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), " El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
