package br.jus.cnj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.model.Usuario;
import br.jus.cnj.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	private static final int PAGE_SIZE = 5;

	@Autowired
	private UsuarioRepository usuarios;
	
	public Page<Usuario> getUsuariosPagination(Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber-1, PAGE_SIZE, Sort.Direction.ASC,"nome","codigo");
		return usuarios.findAll(request);
	}
	
	
}
