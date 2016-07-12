package br.jus.cnj.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	HashSet<User> userList = new HashSet<User>();
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	public String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails){
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	public void getCurrentUser(String usuario){
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		List<Object> principals = sessionRegistry.getAllPrincipals();
		for (Object principal: principals){
			if (principal instanceof User){
				userList.add((User)principal);
			}
		}
		
		for (User user: userList){
			if (user.getUsername().equals(usuario)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
				ctx.setAuthentication(authentication);				
			}
		}		
	}
	
	
}
