package com.prueba.springboot.app.inventario.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.repository.MercanciaRepository;

@Service
public class MercanciaServiceImpl implements MercanciaService {
	
	@Autowired
	private MercanciaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Mercancia> findAll() {		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Mercancia> findByid(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public List<Mercancia> findByNombreProducto(String nombreProducto) {		
		return repository.findByNombreProducto(nombreProducto);
	}
	

	@Override
	@Transactional
	public Mercancia save(Mercancia mercanica) {
		return repository.save(mercanica);
		
	}

	@Override
	@Transactional
	public void deleteByid(Long id) {
		repository.deleteById(id);
	}

	

	

}
