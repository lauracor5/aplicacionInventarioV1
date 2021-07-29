package com.prueba.springboot.app.inventario.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mercancias")
public class Mercancia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nombre;
	@NotNull
	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@Column (name = "fecha_ingreso")
	@NotNull
	private Date fechaIngreso;

	@Temporal(TemporalType.DATE)
	@Column (name = "fecha_modificacion")
	private Date fechaModificacion;

	@JsonIgnoreProperties(value = {"mercanciasRegistradas"})
	@ManyToOne()
	@JoinColumn(name = "usuario_registro_id")
	@NotNull
	private Usuario usuarioRegistro;

	@JsonIgnoreProperties(value = {"mercanciasActualizadas"})
	@ManyToOne()
	@JoinColumn(name = "usuario_modificacion_id")
	private Usuario usuarioModificacion;

	public Mercancia(Long id, String nombre, Integer cantidad, Date fechaIngreso, Date fechaModificacion, Usuario usuarioRegistro, Usuario usuarioModificacion) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.fechaModificacion = fechaModificacion;
		this.usuarioRegistro = usuarioRegistro;
		this.usuarioModificacion = usuarioModificacion;
	}

	public Mercancia(){

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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(!(obj instanceof Mercancia)){
			return false;
		}

		Mercancia instMercancia = (Mercancia) obj;
		return this.id !=null && this.id.equals(instMercancia.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, cantidad, fechaIngreso, fechaModificacion, usuarioRegistro, usuarioModificacion);
	}
}
