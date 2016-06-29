package br.jus.cnj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cnj.model.Convidado;
import br.jus.cnj.repository.*;
import br.jus.cnj.service.ConvidadoService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository convidados;
	
	@Autowired
	private ConvidadoService convidadosService;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public String novoConvidado(@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
        if (!result.hasErrors()) {
            try {
                convidados.save(convidado);
                return "redirect:/mostrartodos/1";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrartodos/1";
            }
        } else {
            model.addAttribute("convidados", convidados.findAllOrdered());
            return "redirect:/mostrartodos/1";
        }		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/atualizar", method=RequestMethod.POST)
	public String atualizar(@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
		if (! result.hasErrors()){
            try {
                convidados.save(convidado);
                model.addAttribute("convidados", convidados.findAllOrdered());
                return "redirect:/mostrartodos/1";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrartodos/1";
            }		
        } else {
            model.addAttribute("convidados", convidados.findAllOrdered());
            return "redirect:/mostrartodos/1";
		}
	}
	
	@RequestMapping(value="/atualizar/{codigo}", method=RequestMethod.GET)
	public String editar(@PathVariable("codigo") int codigo,@ModelAttribute("convidado") Convidado convidado, Model model){
		convidado = convidados.findOne(codigo);
		model.addAttribute("convidado", convidado);
		return "mostrarEdicao";
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}	
	
	
	@RequestMapping(value="/excluir/{codigo}", method=RequestMethod.GET)
	public String remover(@PathVariable("codigo") int codigo, Model model){
		Convidado temp = convidados.findOne(codigo);
		convidados.delete(temp);
		model.addAttribute("convidados",convidados.findAllOrdered());
		return "redirect:/mostrartodos/1";
	}
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String cadastrar(Model model){
		Convidado convidado = new Convidado();
		model.addAttribute("convidado",convidado);
		return "cadastrar";
	}
	
	@RequestMapping(value="/mostrartodos", method=RequestMethod.GET)
	public String mostraTodos(Model model){
		model.addAttribute("convidados", convidados.findAllOrdered());
		return "mostrartodosPaginado";
	}
	
	@RequestMapping(value="/mostrartodos/{pageNumber}", method=RequestMethod.GET)
	public String mostraTodosPaginado(@PathVariable("pageNumber") Integer pageNumber, Model model) {
		if (pageNumber < 0 || pageNumber > convidados.count())
			pageNumber = 1;
		
		Page<Convidado> page = convidadosService.getConvidadosPagination(pageNumber);
			
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 5, page.getTotalPages());
		
		model.addAttribute("convidadosServ", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("convidados", page.getContent());
		
		return "mostrartodosPaginado";
	}
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getPhrase(){
		return "index";
	}
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails){
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
}
