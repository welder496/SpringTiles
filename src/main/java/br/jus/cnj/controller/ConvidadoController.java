package br.jus.cnj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cnj.dao.interfaces.Dao;
import br.jus.cnj.model.Convidado;

@Controller
public class ConvidadoController {

	@Autowired
	private Dao<Convidado> convidados;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public String novoConvidado(@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
        if (!result.hasErrors()) {
            try {
                convidados.register(convidado);
                return "redirect:/mostrartodos";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrderedByName());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrartodos";
            }
        } else {
            model.addAttribute("convidados", convidados.findAllOrderedByName());
            return "redirect:/mostrartodos";
        }		
	}
	
	@RequestMapping(value="/atualizar", method=RequestMethod.POST)
	public String atualizar(@Valid @ModelAttribute("convidado") Convidado convidado, BindingResult result, Model model){
		if (! result.hasErrors()){
            try {
                convidados.register(convidado);
                model.addAttribute("convidados", convidados.findAllOrderedByName());
                return "redirect:/mostrartodos";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("convidados", convidados.findAllOrderedByName());
                model.addAttribute("error", e.getCause().getCause());
                return "redirect:/mostrartodos";
            }		
        } else {
            model.addAttribute("convidados", convidados.findAllOrderedByName());
            return "redirect:/mostrartodos";
		}
	}
	
	@RequestMapping(value="/atualizar/{codigo}", method=RequestMethod.GET)
	public String editar(@PathVariable("codigo") int codigo,@ModelAttribute("convidado") Convidado convidado, Model model){
		convidado = convidados.findById(codigo);
		model.addAttribute("convidado", convidado);
		return "mostrarEdicao";
	}
	
	@RequestMapping(value="/excluir/{codigo}", method=RequestMethod.GET)
	public String remover(@PathVariable("codigo") int codigo, Model model){
		Convidado temp = convidados.findById(codigo);
		convidados.remove(temp);
		model.addAttribute("convidados",convidados.findAllOrderedByName());
		return "redirect:/mostrartodos";
	}
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String cadastrar(Model model){
		Convidado convidado = new Convidado();
		model.addAttribute("convidado",convidado);
		return "cadastrar";
	}
	
	@RequestMapping(value="/mostrartodos", method=RequestMethod.GET)
	public String mostraTodos(Model model){
		model.addAttribute("convidados", convidados.findAllOrderedByName());
		return "mostrartodos";
	}
	
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getPhrase(){
		return "index";
	}
	
}
