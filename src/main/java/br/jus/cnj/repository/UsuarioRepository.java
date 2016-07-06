package br.jus.cnj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.cnj.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

}
