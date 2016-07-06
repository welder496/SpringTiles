package br.jus.cnj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.cnj.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	@Query("Select u from Usuario u order by u.nome")
	List<Usuario> findAllOrdered();
	
	
}
