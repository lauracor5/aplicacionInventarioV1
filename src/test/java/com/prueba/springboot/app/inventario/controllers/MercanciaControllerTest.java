package com.prueba.springboot.app.inventario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.springboot.app.inventario.Datos;
import com.prueba.springboot.app.inventario.Services.MercanciaService;
import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.exceptions.ExceptionNegocio;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MercanciaController.class)
class MercanciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MercanciaService mercanciaService;

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
        List<Mercancia> mercancias = Arrays.asList(Datos.crearMercancia001().orElseThrow(), Datos.crearMercancia002().orElseThrow(),
                Datos.crearMercancia003().orElseThrow());
        when(mercanciaService.findAll()).thenReturn(mercancias);

        //When
        mockMvc.perform(get("/api/inventario/mercancia").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[0].nombre").value("producto1"))
                .andExpect(jsonPath("$[1].nombre").value("producto2"))
                .andExpect(jsonPath("$[0].cantidad").value(5))
                .andExpect(jsonPath("$[1].cantidad").value(5));

        verify(mercanciaService).findAll();


    }

    @Test
    void findById() throws Exception {
        //Given
        Usuario usuarioRegistro = Datos.crearUsuario001().orElseThrow();
        when(mercanciaService.findById(1L)).thenReturn(Datos.crearMercancia001());

        //when
        mockMvc.perform(get("/api/inventario/mercancia/1").contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("producto1"))
                .andExpect(jsonPath("$.cantidad").value(5))
                .andExpect(jsonPath("$.usuarioRegistro.nombre").value(usuarioRegistro.getNombre()));


        verify(mercanciaService).findById(1L);
    }

    @Test
    void testFindByName() throws Exception {
        //Given
        Usuario usuarioRegistro = Datos.crearUsuario001().orElseThrow();
        when(mercanciaService.findByName("producto1")).thenReturn(Datos.crearMercancia001());

        //when
        mockMvc.perform(get("/api/inventario/mercancia/obtenerPorNombre/producto1").contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("producto1"))
                .andExpect(jsonPath("$.cantidad").value(5))
                .andExpect(jsonPath("$.usuarioRegistro.nombre").value(usuarioRegistro.getNombre()));


        verify(mercanciaService).findByName("producto1");
    }

    @Test
    void testSave() throws Exception {
        //Given
        Usuario usuarioRegistro = Datos.crearUsuario002().orElseThrow();
        Mercancia mercanciaIngresar = new Mercancia(null, "producto4", 7, new Date(), null, usuarioRegistro, null);
        when(mercanciaService.save(any())).then(invocation -> {
            Mercancia m = invocation.getArgument(0);
            m.setId(3L);
            return m;
        });

        //when
        mockMvc.perform(post("/api/inventario/mercancia").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mercanciaIngresar)))
                //Then
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.nombre").value("producto4"))
                .andExpect(jsonPath("$.usuarioRegistro.nombre").value("Sofía"));

        verify(mercanciaService).save(any());
    }

    @Test
    void testUpdate() throws Exception {
        //Given
        Usuario usuarioModifica = Datos.crearUsuario002().orElseThrow();
        Mercancia mercanciaActualizar = Datos.crearMercancia001().orElseThrow();
        mercanciaActualizar.setUsuarioModificacion(usuarioModifica);
        mercanciaActualizar.setNombre("producto44");
        when(mercanciaService.save(any())).then(invocation -> {
            Mercancia m = invocation.getArgument(0);
            return m;
        });

        //when
        mockMvc.perform(post("/api/inventario/mercancia").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mercanciaActualizar)))
                //Then
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("producto44"))
                .andExpect(jsonPath("$.usuarioRegistro.nombre").value("Laura"))
                .andExpect(jsonPath("$.usuarioModificacion.nombre").value("Sofía"));

        verify(mercanciaService).save(any());
    }

    @Test
    void testDelete() {
        Usuario usuario = Datos.crearUsuario002().orElseThrow();
        mercanciaService.deleteByid(1L, 1L);
        verify(mercanciaService).deleteByid(1L, 1L);
    }
}