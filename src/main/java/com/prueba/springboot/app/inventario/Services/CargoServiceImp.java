package com.prueba.springboot.app.inventario.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.springboot.app.inventario.models.entity.Cargo;
import com.prueba.springboot.app.inventario.models.repository.CargoRepository;

@Service
public class CargoServiceImp  implements CargoService{
	
	@Autowired
	private CargoRepository repository;

	@Override
	@Transactional
	public Cargo save(Cargo cargo) {
		return repository.save(cargo);
	}

	@Override
	public Iterable<Cargo> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Cargo> findByID(Long id) {
		return repository.findById(id);
	}
	
	
	
	

}
