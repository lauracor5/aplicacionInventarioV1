package com.prueba.springboot.app.inventario.Services;

import com.prueba.springboot.app.inventario.Datos;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import com.prueba.springboot.app.inventario.models.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceTest {

    @MockBean
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Test
    void testFindAll() {
        //Given
        List<Usuario>datosUsuarios = Arrays.asList(Datos.crearUsuario001().orElseThrow(),
                Datos.crearUsuario002().orElseThrow());
            when(usuarioRepository.findAll()).thenReturn(datosUsuarios);

        //when
        List<Usuario>usuarios = usuarioService.findAll();

        //then
        assertFalse(usuarios.isEmpty());
        assertEquals(2, usuarios.size());
        assertTrue(usuarios.contains(Datos.crearUsuario001().orElseThrow()));

        verify(usuarioRepository).findAll();
    }

    @Test
    void testFindById () {
        //Given
            when(usuarioRepository.findById(1L)).thenReturn(Datos.crearUsuario001());
        //When
            Usuario usuario =usuarioService.findByid(1L).orElseThrow();
        //Then
            assertEquals("Laura", usuario.getNombre());
            assertEquals(29, usuario.getEdad());
    }

    @Test
    void testSave() {
        //Given
            Usuario usuarioingresar = new Usuario(null, "Claudia", 32);
            when(usuarioRepository.save(any())).then(invocation -> {
                Usuario u = invocation.getArgument(0);
                u.setId(3L);
                return u;
            });
        //When
            Usuario usuario = usuarioService.save(usuarioingresar);
        //Then
            assertEquals("Claudia", usuario.getNombre());
            assertEquals(32, usuario.getEdad());
    }

    @Test
    void testDelete() {
        when(usuarioRepository.findById(1L)).thenReturn(Datos.crearUsuario001());
        usuarioService.deleteByid(1L);

    }
}