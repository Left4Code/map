package service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Mandate;
import entities.Project;
import entities.Ressource;
import entities.Skills;
import enumerator.MandateType;

@Stateless
public class MandateBusiness implements MandateBusinessLocale {

	@PersistenceContext(unitName="l4c_map-v2-ejb")
	EntityManager em;
	@Override
	public void addMandate(Mandate m) {
		int periode = (int) ((m.getDateEnd().getTime()-m.getDateBegin().getTime())/(1000*60*60*24));
		m.setDuration(periode);
		//m.setCost(fraisMandat(m));
		m.setArchive(false);
		em.persist(m);
		//Ressource r = em.find(Ressource.class, m.getMandatepk().getIdRessource());
		//sendMail(r.getName());
	}

	@Override
	public void deleteMandate(Mandate m) {
		em.remove(em.merge(m));
		
	}

	@Override
	public void updateMandate(Mandate m) {
		em.merge(m);
	}

	@Override
	public List<Mandate> getMandates() {
		TypedQuery<Mandate> query = em.createQuery("select DISTINCT m from Mandate m",Mandate.class);
		return query.getResultList();
	}
	

	@Override
	public List<Mandate> getMandateByProject(int p) {
		TypedQuery<Mandate> query = em.createQuery("select DISTINCT m from Mandate m where m.mandatepk.idProject=:projectid",Mandate.class);
		query.setParameter("projectid",p);
		return query.getResultList();
	}

	@Override
	public List<Mandate> getMandateByResources(int r) {
		TypedQuery<Mandate> query = em.createQuery("select DISTINCT m from Mandate m where m.mandatepk.idRessource=:ressourceid",Mandate.class);
		query.setParameter("ressourceid",r);
		return query.getResultList();
	}

	@Override
	public List<Mandate> getEndingMandates() {
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m WHERE DATEDIFF(m.dateEnd,DATE( NOW() ))BETWEEN 0 AND 40",Mandate.class);
		return query.getResultList();
	}

	@Override
	public float fraisMandat(Mandate m) {
		Ressource r = em.find(Ressource.class, m.getMandatepk().getIdRessource());
		return (float) (((r.getCost()/30)*m.getDuration())*1.8);
	}

	@Override
	public void stockMandate() {
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m WHERE DATEDIFF(m.dateEnd,DATE( NOW() ))<0",Mandate.class);
		List <Mandate> mandateList = query.getResultList();
		if (mandateList.size()>0)
			
			mandateList.stream().forEach(e-> {e.setArchive(true);em.merge(e);});
	}

	@Override
	public List<Mandate> getArchivedMandate() {
		TypedQuery<Mandate> query = em.createQuery("select DISTINCT m from Mandate m",Mandate.class);
		return query.getResultList().stream().filter(m->m.getArchive()).collect(Collectors.toList());
	}

	@Override
	public List<Mandate> getCurrentMandate() {	
		return getMandates().stream().filter(e->e.getArchive() == false).collect(Collectors.toList());
	}

	@Override
	public List<Mandate> getArchivedMandateByProject(int p) {
		return getArchivedMandate().stream().filter(m->m.getMandatepk().getIdProject() == p).collect(Collectors.toList());
	}

	@Override
	public List<Mandate> getArchivedMandateByRessource(int r) {
		return getArchivedMandate().stream().filter(m->m.getMandatepk().getIdRessource() == r).collect(Collectors.toList());
	}

	@Override
	public List<Ressource> getRessources(int projectid) {
		Project p = em.find(Project.class, projectid);
		Map<Ressource, Integer> result = new HashMap<Ressource, Integer>();
		TypedQuery<Ressource> queryR =em.createQuery("select r from Ressource r",Ressource.class);
		List <Ressource> rlist = queryR.getResultList();
		p.getRequiredSkills().forEach(s->{
			rlist.forEach(r ->{
				r.getSkills().forEach(rs->{
					if (rs.getIdSkills() == s.getIdSkills()) {
						if(result.containsKey(r))
							result.put(r, result.get(r)+1);
						else
							result.put(r,1);
					}});
			});
		});
		result.keySet().forEach(e -> System.out.print(e.getId()+" - "+e.getName()+" |"));
		System.out.println(result.values());
		result.keySet().forEach(r->{
			TypedQuery<Mandate> query = em.createQuery("select m from Mandate m where m.mandatepk.idRessource=:id AND DATEDIFF(m.dateEnd,"+p.getDateBegin()+")<0",Mandate.class);
			query.setParameter("id",r.getId());
			if(query.getResultList() != null) {
				query.getResultList().forEach(m ->{
					//to do nfasa5 les ressource eli mawjoudine fil m mil map result
					Ressource ressource = em.find(Ressource.class, m.getMandatepk().getIdRessource());
					result.remove(ressource);			
				});
			}
		});
		System.out.println("aprés vecteur disponibilité :");
		result.keySet().forEach(e -> System.out.print(e.getId()+" - "+e.getName()+"|"));
		System.out.println(result.values());
		return result.keySet().stream().collect(Collectors.toList());
	}
	
}
