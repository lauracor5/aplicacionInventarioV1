package com.prueba.springboot.app.inventario.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.springboot.app.inventario.Datos;
import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception {
        //Given
        List<Usuario> usuarios = Arrays.asList(Datos.crearUsuario001().orElseThrow(), Datos.crearUsuario002().orElseThrow());
        when(usuarioService.findAll()).thenReturn(usuarios);
        //when
        mockMvc.perform(get("/api/inventario/usuario").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(jsonPath("$.[0].nombre").value("Laura"))
                .andExpect(jsonPath("$[0].edad").value(29))
                .andExpect(content().json(objectMapper.writeValueAsString(usuarios)));

        verify(usuarioService).findAll();

    }

    @Test
    void testFindById() throws Exception {
        //Given
        when(usuarioService.findByid(1L)).thenReturn(Datos.crearUsuario001());

        //When
        mockMvc.perform(get("/api/inventario/usuario/1").contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Laura"))
                .andExpect(jsonPath("$.edad").value("29"));

        verify(usuarioService).findByid(1L);

    }

    @Test
    void save() throws Exception {
        Usuario usuario = new Usuario(null, "Claudia", 32);
        when(usuarioService.save(any())).then(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setId(3L);
            return u;
        });

        //when
        mockMvc.perform(post("/api/inventario/usuario").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))

                //then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.nombre").value("Claudia"))
                .andExpect(jsonPath("$.edad").value(32));
        verify(usuarioService).save(any());
    }

    @Test
    void actualizar() throws Exception {
        //Given
        when(usuarioService.findByid(1L)).thenReturn(Datos.crearUsuario001());
        Usuario usuario = Datos.crearUsuario001().orElseThrow();
        usuario.setNombre("ClaudiaL");
        usuario.setEdad(32);
        when(usuarioService.save(any())).then(invocation -> {
            Usuario u = invocation.getArgument(0);
            return u;
        });

        //when
        mockMvc.perform(put("/api/inventario/usuario/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))

                //then
                .andExpect(status().isCreated())
                //Revisar el content
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("ClaudiaL"))
                .andExpect(jsonPath("$.edad").value(32))
                .andExpect(content().json(objectMapper.writeValueAsString(usuario)));

        verify(usuarioService).save(any());


    }
}