package com.mugnol.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mugnol.cursomc.domain.Categoria;
import com.mugnol.cursomc.repositories.CategoriaRepository;
import com.mugnol.cursomc.services.exception.DataIntegrityException;
import com.mugnol.cursomc.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired // Automaticamente instanciada pelo Spring
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId()); // Check exists
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id); // Check exists
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos.");
		}
		
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
}
