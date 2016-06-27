package br.jus.cnj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.model.Convidado;
import br.jus.cnj.repository.ConvidadoRepository;

@Service
@Transactional
public class ConvidadoService {
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private ConvidadoRepository convidados;
	
	public Page<Convidado> getConvidadosPagination(Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber-1, PAGE_SIZE, Sort.Direction.ASC,"nome","codigo");
		return convidados.findAll(request);
	}
	
}
