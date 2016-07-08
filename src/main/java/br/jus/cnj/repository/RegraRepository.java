package br.jus.cnj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.cnj.model.Regra;

public interface RegraRepository extends JpaRepository<Regra,Integer>{

	@Query("Select r.descricao from Regra r order by r.descricao")
	List<String> findAllByDescription();
	
	@Query("Select r from Regra r where r.descricao=?")
	Regra findRegraByDescription(String description);
	
	@Query("Select r.descricao from Regra r where r.codigo=?")	
	String findDescriptionByCodigo(Integer codigo);
	
}
