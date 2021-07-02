package com.prueba.springboot.app.inventario.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cargo cargo;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	
	@OneToMany(mappedBy = "usuarioRegistro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mercancia> mercancias;

	public Usuario() {
		mercancias = new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.fechaIngreso = new Date();
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<Mercancia> getMercancias() {
		return mercancias;
	}
	

	public void setMercancias(List<Mercancia> mercancias) {
		this.mercancias = mercancias;
	}
	
	public void addMercancia(Mercancia mercancia) {
		mercancias.add(mercancia);
	}
	
	public void removeMercancia(Mercancia mercancia) {
		mercancias.remove(mercancia);
	}

}
