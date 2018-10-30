package business;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.HistoricalMandate;
import entities.Mandate;

@Stateless
public class HistoricalMandateBusiness implements HistoricalMandateBusinessLocal {

	@PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em;
	
	@Override
	public void addHistoricalMandate() {
		Date d=new Date();
		java.sql.Date date = new java.sql.Date(d.getTime());
		MandateBusiness MB = new MandateBusiness();
		List <Mandate> outdatedMandates = MB.getMandates().stream().filter(e-> (e.getDateEnd().compareTo(date)<0)).collect(Collectors.toList());
		outdatedMandates.stream().forEach(e->em.persist(e));
	}

	@Override
	public List<HistoricalMandate> getHistoricalMandate() {
		TypedQuery<HistoricalMandate> query = em.createQuery("select DISTINCT h from HistoricalMandate h",HistoricalMandate.class);
		return query.getResultList();
	}
	

}
