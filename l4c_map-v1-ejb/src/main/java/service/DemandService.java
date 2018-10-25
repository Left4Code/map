package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Demand;

@Stateless
public class DemandService implements DemandServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public int insertDemand(Demand demand) {
		em.persist(demand);
		return demand.getIdDemand();
	}

}
