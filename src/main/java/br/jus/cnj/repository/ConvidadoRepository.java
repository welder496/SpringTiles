package br.jus.cnj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.cnj.model.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado,Integer> {

	@Query("Select c from Convidado c order by c.nome")
	List<Convidado> findAllOrdered();

}
