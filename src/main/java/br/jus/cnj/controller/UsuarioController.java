package br.jus.cnj.controller;

import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.jus.cnj.controller.validators.UsuarioRegraValidator;
import br.jus.cnj.model.Regra;
import br.jus.cnj.model.Usuario;
import br.jus.cnj.repository.RegraRepository;
import br.jus.cnj.repository.UsuarioRepository;
import br.jus.cnj.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRegraValidator usuarioRegraValidator;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired 
	private RegraRepository regras;
	
	@Autowired
	private UsuarioService usuariosService;
	
	@RequestMapping(value="/mostrarUsuarios/{pageNumber}", method=RequestMethod.GET)
	public String mostrarUsuarios(@RequestParam(value="usuario", required=false) String usr,@PathVariable("pageNumber") Integer pageNumber, Model model) {	
		
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
		model.addAttribute("usr",usr);
		
		return "mostrarUsuariosPaginado";
	}
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public String cadastrarUsuario(@RequestParam(value="usuario", required=false) String usr,Model model){
			Usuario usuario = new Usuario();			
			
			LinkedHashMap<Integer,String> regrasList = new LinkedHashMap<Integer,String>();
			regrasList.put(0,"--Selecionar uma opção--");
			for (Regra regra:regras.findAll()){
				regrasList.put(regra.getCodigo(), regra.getDescricao());
			}
			
			model.addAttribute("regras",regrasList);
			model.addAttribute("usuario",usuario);
			model.addAttribute("usr",usr);		
			return "cadastrarUsuario";
	}
	
	@RequestMapping(value="/excluirUsuario/{codigo}", method=RequestMethod.GET)
	public String removerUsuario(@RequestParam(value="usuario", required=false) String usr,@PathVariable("codigo") int codigo, Model model){
		Usuario temp = usuarios.findOne(codigo);
		usuarios.delete(temp);
		model.addAttribute("convidados",usuarios.findAllOrdered());
		return "redirect:/mostrarUsuarios/1?usuario="+usr;
	}
	
	@RequestMapping(value="/atualizarUsuario/{codigo}", method=RequestMethod.GET)
	public String atualizarUsuario(@RequestParam(value="usuario", required=false) String usr,@PathVariable("codigo") int codigo,@ModelAttribute("usuario") Usuario usuario, Model model){
		usuario = usuarios.findOne(codigo);

		LinkedHashMap<Integer,String> regrasList = new LinkedHashMap<Integer,String>();
		regrasList.put(0,"--Selecionar uma opção--");
		for (Regra regra:regras.findAll()){
			regrasList.put(regra.getCodigo(), regra.getDescricao());
		}		
		model.addAttribute("regras", regrasList);
		model.addAttribute("usuario", usuario);
		model.addAttribute("usr",usr);
		return "mostrarEdicaoUsuario";
	}	
	
	@RequestMapping(value="/atualizarUsuario", method=RequestMethod.POST)
	public String atualizarUsuario(@RequestParam(value="usuario", required=false) String usr,@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
		usuarioRegraValidator.validate(usuario, result);
		if (! result.hasErrors()){
            try {
            	usuarios.save(usuario);
                model.addAttribute("usuarios", usuarios.findAllOrdered());
                return "redirect:/mostrarUsuarios/1?usuario="+usr;
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("usuarios", usuarios.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrarUsuarios/1?usuario="+usr;
            }		
        } else {
			LinkedHashMap<Integer,String> regrasList = new LinkedHashMap<Integer,String>();
			regrasList.put(0,"--Selecionar uma opção--");
			for (Regra regra:regras.findAll()){
				regrasList.put(regra.getCodigo(), regra.getDescricao());
			}			
			model.addAttribute("regras", regrasList);
			return "mostrarEdicaoUsuario";
		}
	}	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public String salvarUsuario(@RequestParam(value="usuario", required=false) String usr,@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
        usuarioRegraValidator.validate(usuario, result);
		if (!result.hasErrors()) {
            try {
            	usuarios.save(usuario);
                return "redirect:/mostrarUsuarios/1?usuario="+usr;
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("usuarios", usuarios.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrarUsuarios/1?usuario="+usr;
            }
        } else {
			LinkedHashMap<Integer,String> regrasList = new LinkedHashMap<Integer,String>();
			regrasList.put(0,"--Selecionar uma opção--");
			for (Regra regra:regras.findAll()){
				regrasList.put(regra.getCodigo(), regra.getDescricao());
			}			
			model.addAttribute("regras", regrasList);
			return "cadastrarUsuario";
        }		
	}
	
}
