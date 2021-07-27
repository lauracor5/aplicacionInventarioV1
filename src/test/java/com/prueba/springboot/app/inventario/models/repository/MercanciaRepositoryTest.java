package com.prueba.springboot.app.inventario.models.repository;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MercanciaRepositoryTest {

    @Autowired
    MercanciaRepository mercanciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void testFindById() {
        Optional<Mercancia>mercancia = mercanciaRepository.findById(1L);
        assertTrue(mercancia.isPresent());
        assertEquals("producto5",  mercancia.orElseThrow().getNombre());

    }

    @Test
    void testFindByName() {
        Optional<Mercancia>mercancia = mercanciaRepository.findByName("producto5");
        assertTrue(mercancia.isPresent());
        assertEquals("producto5", mercancia.orElseThrow().getNombre());

    }

    @Test
    void testFindMercanciaThrowException() {
        Optional<Mercancia>mercancia = mercanciaRepository.findByName("producto6");
        assertThrows(NoSuchElementException.class, () -> {
           mercancia.orElseThrow();
        });
        assertFalse(mercancia.isPresent());

    }

    @Test
    void testFindAll() {
        List<Mercancia>lsitaMercancias = mercanciaRepository.findAll();
        assertFalse(lsitaMercancias.isEmpty());
        assertEquals(1, lsitaMercancias.size());
    }

    @Test
    void testSave() {
        Usuario usuario = usuarioRepository.findById(1L).orElseThrow();
        Mercancia mercanciaCrear = new Mercancia(null, "producto1", 9, new Date(), null, usuario, null );

        Mercancia mercancia = mercanciaRepository.save(mercanciaCrear);
        assertEquals("producto1", mercancia.getNombre());
    }

    @Test
    void testSaveUpdate() {
        Usuario usuario = usuarioRepository.findById(1L).orElseThrow();
        Mercancia mercanciaCrear = new Mercancia(null, "producto1", 9, new Date(), null, usuario, null );

        Mercancia mercancia = mercanciaRepository.save(mercanciaCrear);
        assertEquals("producto1", mercancia.getNombre());

        Usuario usuarioModificacion = usuarioRepository.findById(2L).orElseThrow();
        mercancia.setUsuarioModificacion(usuarioModificacion);
        Mercancia mercanciaModificada = mercanciaRepository.save(mercancia);
        assertEquals("Sofía", mercanciaModificada.getUsuarioModificacion().getNombre());

    }

    @Test
    void testDelete() {
        mercanciaRepository.deleteById(1L);
        assertThrows(NoSuchElementException.class, ()->{
            mercanciaRepository.findById(1L).orElseThrow();
        });

    }
}