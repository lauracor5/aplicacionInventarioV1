package com.prueba.springboot.app.inventario;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;

import java.util.Date;
import java.util.Optional;

public class Datos {

    public static Optional<Usuario>crearUsuario001(){
        return Optional.of(new Usuario(1L, "Laura", 29));
    }

    public static Optional<Usuario>crearUsuario002(){
        return Optional.of(new Usuario(2L, "Sofía", 25));
    }

    public static  Optional<Mercancia>crearMercancia001(){
        return Optional.of(new Mercancia(1L,"producto1",5, new Date(), null, crearUsuario001().get(),
                null ));
    }

    public static  Optional<Mercancia>crearMercancia002(){
        return Optional.of(new Mercancia(2L,"producto2",5, new Date(), null, crearUsuario001().get(),
                null ));
    }

    public static  Optional<Mercancia>crearMercancia003(){
        return Optional.of(new Mercancia(3L,"producto3",5, new Date(), null, null,
                null ));
    }
}
