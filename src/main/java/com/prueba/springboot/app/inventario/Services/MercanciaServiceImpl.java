package com.prueba.springboot.app.inventario.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.prueba.springboot.app.inventario.exceptions.ExceptionNegocio;
import com.prueba.springboot.app.inventario.models.entity.Usuario;
import com.prueba.springboot.app.inventario.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.repository.MercanciaRepository;

@Service
public class MercanciaServiceImpl implements MercanciaService {

    @Autowired
    private MercanciaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Mercancia> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mercancia> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mercancia> findByName(String nombre) {
        return repository.findByName(nombre);
    }

    @Override
    @Transactional
    public Mercancia save(Mercancia mercancia) {
        //Obtenemos mercancia por nombre
         Optional<Mercancia> optMercanciaIngresar = findByName(mercancia.getNombre());
        //Buscamos si la mercancia existe con el nombre que se ingresa
        if(!optMercanciaIngresar.isEmpty()){
            throw new ExceptionNegocio("Nombre de producto ya existe");
        }

        if(!(mercancia.getFechaIngreso() == null)){
            evaluaFechas(mercancia.getFechaIngreso());
        }
        if(!(mercancia.getFechaModificacion() == null)){
            evaluaFechas(mercancia.getFechaModificacion());
        }
        return repository.save(mercancia);
    }

    @Override
    @Transactional
    public void deleteByid(Long idMercancia, Long idUsuario) {
        Mercancia mercanciaBorrar = repository.findById(idMercancia).orElseThrow();
        //Obtener usuario que tiene registrada la mercancia
        Usuario usuarioBorrar = usuarioRepository.findById(idUsuario).orElseThrow();
        if (!mercanciaBorrar.getUsuarioRegistro().getId().equals(usuarioBorrar.getId())) {
            throw new ExceptionNegocio("El usuario no es el mismo que registró la mercancia");
        }
        repository.deleteById(idMercancia);
    }

    private Date evaluaFechas(Date fecha){
        if (fecha.after(new Date())) {
            throw new ExceptionNegocio("la fecha que ingresó es mayor a la fecha actual");
        }
        return fecha;
    }
}
