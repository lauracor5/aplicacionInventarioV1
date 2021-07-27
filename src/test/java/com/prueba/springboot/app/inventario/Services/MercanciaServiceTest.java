package com.prueba.springboot.app.inventario.Services;

import com.prueba.springboot.app.inventario.Datos;
import com.prueba.springboot.app.inventario.exceptions.ExceptionNegocio;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import com.prueba.springboot.app.inventario.models.repository.MercanciaRepository;
import com.prueba.springboot.app.inventario.models.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MercanciaServiceTest {

    @MockBean
    MercanciaRepository mercanciaRepository;

    @Autowired
    MercanciaService mercanciaService;


    @Test
    void testFindAll() {
        //Given
        List<Mercancia>datosMercancias = Arrays.asList(Datos.crearMercancia001().orElseThrow(),
                Datos.crearMercancia002().orElseThrow());
        when(mercanciaRepository.findAll()).thenReturn(datosMercancias);

        //When
        List<Mercancia>mercancias = mercanciaService.findAll();

        //Then
        assertFalse(mercancias.isEmpty());
        assertEquals(2,mercancias.size());
        assertTrue(mercancias.contains(Datos.crearMercancia001().orElseThrow()));

        verify(mercanciaRepository).findAll();
    }

    @Test
    void testFindById() {
        //Given
        when(mercanciaRepository.findById(1L)).thenReturn(Datos.crearMercancia001());
        //When
        Mercancia mercancia = mercanciaService.findById(1L).orElseThrow();

        //Then
        assertEquals("producto1", mercancia.getNombre());
        assertEquals(5, mercancia.getCantidad());
        assertEquals("Laura", mercancia.getUsuarioRegistro().getNombre());
    }

    @Test
    void testFindByName() {
        //Given
        when(mercanciaRepository.findByName("producto1")).thenReturn(Datos.crearMercancia001());
        //when
        Mercancia mercancia = mercanciaService.findByName("producto1").orElseThrow();
        //then
        assertEquals("producto1", mercancia.getNombre());
        assertEquals(5, mercancia.getCantidad());
        assertEquals("Laura", mercancia.getUsuarioRegistro().getNombre());

    }

    @Test
    void testSave() {
        //Given
        Mercancia mercanciaIngresar = new Mercancia(null, "producto3", 7, new Date(), null,
                Datos.crearUsuario001().orElseThrow(), null);
        when(mercanciaRepository.save(any())).then(invocation -> {
           Mercancia m = invocation.getArgument(0);
           m.setId(3L);
           return m;
        });
        //When
            Mercancia mercancia = mercanciaService.save(mercanciaIngresar);
        //then
            assertEquals("producto3", mercancia.getNombre());
            assertEquals("Laura", mercancia.getUsuarioRegistro().getNombre());
            assertEquals(7, mercancia.getCantidad());
    }

    @Test
    void testSaveNameRepetido() {
        //Given
        when(mercanciaRepository.findByName("producto1")).thenReturn(Datos.crearMercancia001());
        Mercancia mercanciaExiste= mercanciaService.findByName("producto1").orElseThrow();

        Mercancia mercanciaIngresar = new Mercancia(null, "producto1", 7, new Date(), null,
                Datos.crearUsuario001().orElseThrow(), null);
        when(mercanciaRepository.save(any())).then(invocation -> {
            Mercancia m = invocation.getArgument(0);
            m.setId(3L);
            return m;
        });
        //When
        Exception exception= assertThrows(ExceptionNegocio.class, ()->{
             mercanciaService.save(mercanciaIngresar);
        });

    }

    @Test
    void testValidaFecha() throws ParseException {
        //Given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaRegistro = simpleDateFormat.parse("2021-08-27");
        when(mercanciaRepository.findByName("producto1")).thenReturn(Datos.crearMercancia001());
        Mercancia mercanciaExiste= mercanciaService.findByName("producto1").orElseThrow();

        Mercancia mercanciaIngresar = new Mercancia(null, "producto1", 7, fechaRegistro, null,
                Datos.crearUsuario001().orElseThrow(), null);
        when(mercanciaRepository.save(any())).then(invocation -> {
            Mercancia m = invocation.getArgument(0);
            m.setId(3L);
            return m;
        });
        //When
        Exception exception= assertThrows(ExceptionNegocio.class, ()->{
            mercanciaService.save(mercanciaIngresar);
        });

    }

    @Test
    void testDeleteUsuarioRegistro(){
        //Debe borrar el mismo usuario que la registro
        when(mercanciaRepository.findById(1L)).thenReturn(Datos.crearMercancia001());
            mercanciaService.deleteByid(1L, 1L);

    }

    @Test
    void testDeleteUsuarioNoRegistro(){
        //Debe borrar el mismo usuario que la registro
        when(mercanciaRepository.findById(1L)).thenReturn(Datos.crearMercancia001());
        Exception exception= assertThrows(ExceptionNegocio.class, ()->{
            mercanciaService.deleteByid(1L, 2L);
        });
    }
}