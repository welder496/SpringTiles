package br.jus.cnj.controller.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.jus.cnj.model.Usuario;

@Component
public class UsuarioRegraValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario)target;
		if (usuario.getRegra().getCodigo()==0){
			errors.rejectValue("regra", "regra.required");
		}	
	}

}
