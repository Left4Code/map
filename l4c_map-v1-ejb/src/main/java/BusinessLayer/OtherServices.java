package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import enumerator.MandateType;
import enumerator.TypeRessource;

@Stateless
public class OtherServices implements IOtherServices{
	@PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em;

	
	@Override
	public List<Object> getEmployeVsPigiste() {
		List<Object> l=new ArrayList<>();
		
		TypedQuery<Long> nbEmployee=em.createQuery("select count(r) from Ressource r where r.type = :type ",Long.class);
		nbEmployee.setParameter("type", TypeRessource.Employee);
		long nbEmp=nbEmployee.getSingleResult();
		TypedQuery<Long> nbPigiste=em.createQuery("select count(r) from Ressource r where r.type = :type ",Long.class);
		nbPigiste.setParameter("type", TypeRessource.Pigiste);
		long nbPig=nbPigiste.getSingleResult();
		String nbPigStr="nombre des pigistes : "+nbPig;
		String nbEmpStr="nombre des Employees : "+nbEmp;
		l.add(nbEmpStr);
		l.add(nbPigStr);
		return l;
	}

	@Override
	public List<Object> getMandateVsInterMandate() {
		List<Object> l=new ArrayList<>();
		TypedQuery<Long> nbMandate=em.createQuery("select count(m) from Mandate m where m.mandateType = :type ",Long.class);
		nbMandate.setParameter("type", MandateType.Mandate);
		long nbMan=nbMandate.getSingleResult();
		TypedQuery<Long> nbInterMandate=em.createQuery("select count(m) from Mandate m where m.mandateType = :type ",Long.class);
		nbInterMandate.setParameter("type", MandateType.Inter_Mandate);
		long nbInterMan=nbInterMandate.getSingleResult();
		String nbManStr="nombre des personnes en Mandat: "+nbMan;
		String nbInterManStr="nombre des personnes en inter-mandat: "+nbInterMan;
		
		l.add(nbManStr);
		l.add(nbInterManStr);
		return l;
	}

}
