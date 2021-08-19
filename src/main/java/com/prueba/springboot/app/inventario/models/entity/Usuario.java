package com.prueba.springboot.app.inventario.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotNull
    private Integer edad;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    @NotNull
    private Date fechaIngreso;

    @JsonIgnoreProperties(value = {"usuarioRegistro", "usuarioModificacion", "handler"}, allowSetters = true)
    @OneToMany(mappedBy = "usuarioRegistro", orphanRemoval = true)
    private List<Mercancia> mercanciasRegistradas;

    @JsonIgnoreProperties(value = {"usuarioModificacion", "usuarioRegistro", "handler"},  allowSetters = true)
    @OneToMany(mappedBy = "usuarioModificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mercancia> mercanciasActualizadas;

    public Usuario(Long id, String nombre, Integer edad){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
//        mercanciasRegistradas = new ArrayList<>();
//        mercanciasActualizadas = new ArrayList<>();
    }


    public Usuario(){
        mercanciasRegistradas = new ArrayList<>();
        mercanciasActualizadas = new ArrayList<>();

    }

    @PrePersist
    public void prePersist(){
        fechaIngreso = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Mercancia> getMercanciasRegistradas() {
        return mercanciasRegistradas;
    }

    public void setMercanciasRegistradas(List<Mercancia> mercanciasRegistradas) {
        this.mercanciasRegistradas.clear();
        this.mercanciasRegistradas.forEach(p -> {
            this.addMercanciaRegistradas(p);
        });
    }

    public List<Mercancia> getMercanciasActualizadas() {
        return mercanciasActualizadas;
    }

    public void setMercanciasActualizadas(List<Mercancia> mercanciasActualizadas) {
        this.mercanciasActualizadas.clear();
        this.mercanciasActualizadas.forEach(p -> {
            this.addMercanciaActualizadas(p);
        });

    }

    public void addMercanciaRegistradas(Mercancia mercancia){
        mercanciasRegistradas.add(mercancia);
        mercancia.setUsuarioRegistro(this);
    }

    public void removeMercanciaRegistraas(Mercancia mercancia){
        mercanciasRegistradas.remove(mercancia);
        mercancia.setUsuarioRegistro(null);
    }

    public void addMercanciaActualizadas(Mercancia mercancia){
        mercanciasActualizadas.add(mercancia);
        mercancia.setUsuarioModificacion(this);
    }

    public void removeMercanciaActualizada(Mercancia mercancia){
        mercanciasActualizadas.remove(mercancia);
        mercancia.setUsuarioModificacion(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(edad, usuario.edad) && Objects.equals(fechaIngreso, usuario.fechaIngreso) && Objects.equals(mercanciasRegistradas, usuario.mercanciasRegistradas) && Objects.equals(mercanciasActualizadas, usuario.mercanciasActualizadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, edad, fechaIngreso, mercanciasRegistradas, mercanciasActualizadas);
    }
}
