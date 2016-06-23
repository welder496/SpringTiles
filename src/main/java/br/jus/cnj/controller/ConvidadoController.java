package br.jus.cnj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cnj.dao.interfaces.ConvidadoDao;
import br.jus.cnj.model.Convidado;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoDao convidados;
	
	@RequestMapping(value="/novoConvidado", method=RequestMethod.POST)
	public String novoConvidado(@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
        if (!result.hasErrors()) {
            try {
                convidados.register(convidado);
                return "redirect:/";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrderedByName());
                model.addAttribute("error", e.getCause().getCause());
                return "index";
            }
        } else {
            model.addAttribute("convidados", convidados.findAllOrderedByName());
            return "index";
        }		
	}
	
	@RequestMapping(value="/mostratodos", method=RequestMethod.GET)
	public String mostraTodos(Model model){
		model.addAttribute("convidados", convidados.findAllOrderedByName());
		return "mostratodos";
	}
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getPhrase(){
		return "index";
	}
	
	@RequestMapping(value="/outraPagina", method=RequestMethod.GET)
	public String getNewPhrase(){
		return "outraPagina";
	}
	
}
