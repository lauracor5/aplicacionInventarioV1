package com.prueba.springboot.app.inventario.controllers;

import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin({"http://localhost:4200"})
@RequestMapping("/api/inventario/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping("/pagina")
    public ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok().body(usuarioService.findAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.findByid(id));
    }

    @PostMapping()
        public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
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

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), " El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
