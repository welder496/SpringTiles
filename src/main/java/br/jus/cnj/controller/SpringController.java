package br.jus.cnj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringController {

	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String getPhrase(){
		return "index";
	}
	
	@RequestMapping(value="/outraPagina", method=RequestMethod.GET)
	public String getNewPhrase(){
		return "outraPagina";
	}
	
}
