package br.jus.cnj.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.dao.interfaces.Dao;
import br.jus.cnj.model.Convidado;

@Repository
@Transactional
public class ConvidadoRepository implements Dao<Convidado> {

	@Autowired
	private EntityManager em;
	
	@Override
	public Convidado findById(int id) {
		return em.find(Convidado.class, id);
	}

	@Override
	public Convidado findByName(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Convidado> criteria = cb.createQuery(Convidado.class);
        Root<Convidado> convidado = criteria.from(Convidado.class);
        
		criteria.select(convidado).where(cb.equal(convidado.get("nome"), name));
		
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<Convidado> findAllOrderedByName() {
	       CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Convidado> criteria = cb.createQuery(Convidado.class);
	        Root<Convidado> convidado = criteria.from(Convidado.class);

	        criteria.select(convidado).orderBy(cb.asc(convidado.get("nome")));

	        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void register(Convidado t) {
		em.persist(t);
	}

	@Override
	public void remove(Convidado t) {
		em.remove(em.contains(t) ? t : em.merge(t));
	}

}
