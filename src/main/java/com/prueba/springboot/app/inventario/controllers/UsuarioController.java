package com.prueba.springboot.app.inventario.controllers;

import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/inventario/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.findByid(id));
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario, @PathVariable Long id) {
        Optional<Usuario> optUsuario = usuarioService.findByid(id);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuarioDb = optUsuario.orElseThrow();
        usuarioDb.setEdad(usuario.getEdad());
        usuarioDb.setMercanciasActualizadas(usuario.getMercanciasActualizadas());
        usuarioDb.setNombre(usuario.getNombre());
        usuarioDb.setMercanciasActualizadas(usuario.getMercanciasActualizadas());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDb));
    }

}
