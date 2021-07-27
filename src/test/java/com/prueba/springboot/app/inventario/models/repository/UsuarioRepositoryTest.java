package com.prueba.springboot.app.inventario.models.repository;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    MercanciaRepository mercanciaRepository;


    @Test
    void testFindById() {
        Optional<Usuario>usuario =usuarioRepository.findById(1L);
        assertTrue(usuario.isPresent());
        assertEquals("Laura", usuario.orElseThrow().getNombre());
    }


    @Test
    void testFindByUsuarioThrowException() {
        Optional<Usuario> usuario = usuarioRepository.findById(10L);
        assertThrows(NoSuchElementException.class, () ->{
           usuario.orElseThrow();
        });
        assertFalse(usuario.isPresent());
    }
    @Test
    void testSave() {
        //Give
        Usuario usuarioingresar = new Usuario(null, "Claudia", 32);
        //usuarioRepository.save(usuarioingresar);
        //When
        Usuario usuario = usuarioRepository.save(usuarioingresar);

        //Then
        assertEquals("Claudia", usuario.getNombre());
        assertEquals(32, usuario.getEdad());
    }

    @Test
    void testSaveUpdate() {

        //Give
        Usuario usuarioingresar = new Usuario(null, "Claudia", 32);
        //usuarioRepository.save(usuarioingresar);
        //When
        Usuario usuario = usuarioRepository.save(usuarioingresar);

        //Then
        assertEquals("Claudia", usuario.getNombre());
        assertEquals(32, usuario.getEdad());

        Mercancia mercanciaAgregar = new Mercancia(3L, "producto6", 9, new Date(), null, usuario, null);
        Mercancia mercancia = mercanciaRepository.save(mercanciaAgregar);
        List<Mercancia> listaMercancias = new ArrayList<>();
        listaMercancias.add(mercancia);
        usuario.setMercanciasRegistradas(listaMercancias);
        Usuario usuarioActualizado = usuarioRepository.save(usuario);


        //then Actualizado
        assertEquals(1, usuarioActualizado.getMercanciasRegistradas().size());
        assertTrue(usuarioActualizado.getMercanciasRegistradas().contains(mercancia));
    }

    @Test
    void testDelete() {
        Usuario usuario = usuarioRepository.findById(1L).orElseThrow();
        assertEquals("Laura", usuario.getNombre());

        usuarioRepository.deleteById(1L);

        assertThrows(NoSuchElementException.class, ()->{
            usuarioRepository.findById(1L).orElseThrow();
        });
    }
}