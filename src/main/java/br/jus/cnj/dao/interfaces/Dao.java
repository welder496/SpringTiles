package br.jus.cnj.dao.interfaces;

import java.util.List;

import br.jus.cnj.model.Convidado;

public interface Dao<T> {

    public T findById(int id);

    public T findByName(String name);

    public List<T> findAllOrderedByName();

    public void register(T t);	
	
}
