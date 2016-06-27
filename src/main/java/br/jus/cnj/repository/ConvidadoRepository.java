package br.jus.cnj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.cnj.model.Convidado;

interface ConvidadoRepository extends JpaRepository<Convidado,Integer> {

/*	
	@Autowired
	private EntityManager em;
	
	@Override
	public Convidado findById(Integer id) {
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
		em.persist(em.contains(t) ? t: em.merge(t));
	}

	@Override
	public void remove(Convidado t) {
		em.remove(em.contains(t) ? t : em.merge(t));
	}

	@Override
	public void update(Convidado t) {
		em.persist(t);
	}
*/
}
