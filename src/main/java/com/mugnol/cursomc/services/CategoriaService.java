package com.mugnol.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mugnol.cursomc.domain.Categoria;
import com.mugnol.cursomc.repositories.CategoriaRepository;
import com.mugnol.cursomc.services.exception.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired // Automaticamente instanciada pelo Spring
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
