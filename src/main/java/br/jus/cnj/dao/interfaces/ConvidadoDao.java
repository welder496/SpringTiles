package br.jus.cnj.dao.interfaces;

import java.util.List;

import br.jus.cnj.model.Convidado;

public interface ConvidadoDao {

    public Convidado findById(int id);

    public Convidado findByName(String name);

    public List<Convidado> findAllOrderedByName();

    public void register(Convidado t);	
	
}
