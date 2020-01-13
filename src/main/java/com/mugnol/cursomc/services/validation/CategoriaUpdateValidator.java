package com.mugnol.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mugnol.cursomc.domain.Categoria;
import com.mugnol.cursomc.dto.CategoriaDTO;
import com.mugnol.cursomc.repositories.CategoriaRepository;
import com.mugnol.cursomc.resources.exception.FieldMessage;

public class CategoriaUpdateValidator implements ConstraintValidator<CategoriaUpdate, CategoriaDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CategoriaRepository repo;
	
	@Override
	public void initialize(CategoriaUpdate ann) {
	}

	@Override
	public boolean isValid(CategoriaDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)  request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		Categoria categoria = repo.findByNome(objDto.getNome());
		if (categoria != null && !categoria.getId().equals(uriId)) {
			list.add(new FieldMessage("nome", "Nome já existente"));
		}
		
		// Insere as mensagens no contexto do Spring, Erros do Spring - MM
		// e.getBindingResult().getFieldErrors()
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}