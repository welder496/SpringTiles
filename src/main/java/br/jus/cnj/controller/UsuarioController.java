package br.jus.cnj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.jus.cnj.model.Usuario;
import br.jus.cnj.repository.UsuarioRepository;
import br.jus.cnj.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private UsuarioService usuariosService;
	
	@RequestMapping(value="/mostrarUsuarios/{pageNumber}", method=RequestMethod.GET)
	public String mostrarUsuarios(@RequestParam(value="usuario", required=false) String usuario,@PathVariable("pageNumber") Integer pageNumber, Model model) {	
		
		Page<Usuario> page = usuariosService.getUsuariosPagination(pageNumber);
			
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 5, page.getTotalPages());
		
		model.addAttribute("usuariosServ", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPages", page.getTotalPages() == 0 ? 1: page.getTotalPages());
		model.addAttribute("usuarios", page.getContent());
		model.addAttribute("usuario",usuario);
		
		return "mostrarUsuariosPaginado";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String cadastrarUsuario(){
		return "cadastrarUsuario";
	}
	
}
