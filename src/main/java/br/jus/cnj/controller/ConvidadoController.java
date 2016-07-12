package br.jus.cnj.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
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
import org.springframework.web.bind.annotation.RequestParam;

import br.jus.cnj.model.Convidado;
import br.jus.cnj.repository.ConvidadoRepository;
import br.jus.cnj.service.ConvidadoService;
import br.jus.cnj.service.SecurityService;

@Controller
public class ConvidadoController {

	//HashSet<User> userList = new HashSet<User>();
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private ConvidadoRepository convidados;
	
	@Autowired
	private ConvidadoService convidadosService;
	
	//@Autowired
	//@Qualifier("sessionRegistry")
	//private SessionRegistry sessionRegistry;
	
	@RequestMapping(value="/cadastrarConvidado", method=RequestMethod.POST)
	public String novoConvidado(@RequestParam(value="usuario", required=false) String usuario,@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
		securityService.getCurrentUser(usuario);
		if (!result.hasErrors()) {
            try {
                convidados.save(convidado);
                return "redirect:/mostrarConvidados/1?usuario="+usuario;
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrarConvidados/1?usuario="+usuario;
            }
        } else {
        	model.addAttribute("usuario", usuario);
            return "cadastrarConvidado";
        }		
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value="usuario", required=false) String usuario){
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (@RequestParam(value="usuario", required=false) String usuario,HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }    
	    return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/atualizarConvidado", method=RequestMethod.POST)
	public String atualizarConvidado(@RequestParam(value="usuario", required=false) String usuario,@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
		securityService.getCurrentUser(usuario);
		if (! result.hasErrors()){
            try {
                convidados.save(convidado);
                model.addAttribute("convidados", convidados.findAllOrdered());
                return "redirect:/mostrarConvidados/1?usuario="+usuario;
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrdered());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrarConvidados/1?usuario="+usuario;
            }		
        } else {
        	model.addAttribute("usuario",usuario);
            return "mostrarEdicaoConvidado";
		}
	}
	
	@RequestMapping(value="/atualizarConvidado/{codigo}", method=RequestMethod.GET)
	public String editarConvidado(@RequestParam(value="usuario", required=false) String usuario,@PathVariable("codigo") int codigo,@ModelAttribute("convidado") Convidado convidado, Model model){
		convidado = convidados.findOne(codigo);
		model.addAttribute("convidado", convidado);
		model.addAttribute("usuario",usuario);
		return "mostrarEdicaoConvidado";
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		model.addAttribute("user", securityService.getPrincipal());
		return "accessDenied";
	}	
	
	
	@RequestMapping(value="/excluirConvidado/{codigo}", method=RequestMethod.GET)
	public String removerConvidado(@RequestParam(value="usuario", required=false) String usuario,@PathVariable("codigo") int codigo, Model model){
		Convidado temp = convidados.findOne(codigo);
		convidados.delete(temp);
		model.addAttribute("convidados",convidados.findAllOrdered());
		return "redirect:/mostrarConvidados/1?usuario="+usuario;
	}
	
	@RequestMapping(value="/cadastrarConvidado", method=RequestMethod.GET)
	public String cadastrarConvidado(@RequestParam(value="usuario", required=false) String usuario,Model model){
		Convidado convidado = new Convidado();
		model.addAttribute("convidado",convidado);
		model.addAttribute("usuario",usuario);
		return "cadastrarConvidado";
	}
	
	@RequestMapping(value="/mostrarConvidados/{pageNumber}", method=RequestMethod.GET)
	public String mostraConvidadosPaginado(@RequestParam(value="usuario", required=false) String usuario,@PathVariable("pageNumber") Integer pageNumber, Model model) {
		securityService.getCurrentUser(usuario);
		
		Page<Convidado> page = convidadosService.getConvidadosPagination(pageNumber);
			
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 5, page.getTotalPages());
		
		model.addAttribute("convidadosServ", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPages", page.getTotalPages() == 0 ? 1: page.getTotalPages());		
		model.addAttribute("convidados", page.getContent());
		model.addAttribute("usuario",usuario);
		
		return "mostrarConvidadosPaginado";
	}
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String index(@RequestParam(value="usuario", required=false) String usuario,HttpServletRequest request,HttpServletResponse response, Model model){	
		
		securityService.getCurrentUser(usuario);
		
		model.addAttribute("usuario",securityService.getPrincipal());		
	    return "index";
	}
		
}
