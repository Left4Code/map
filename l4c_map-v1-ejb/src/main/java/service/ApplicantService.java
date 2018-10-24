package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import entities.Applicant;
import entities.User;
import enumerator.ApplicantState;

@Stateless
public class ApplicantService implements ApplicantServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;
	
	@Override
	public int insertApplicant(Applicant A) {
		em.persist(A);
		return A.getId();
	}

	@Override
	public boolean deleteApplicant(int idApplicant) {
		Applicant applicant = em.find(Applicant.class, idApplicant);
		if(applicant != null){
			em.remove(applicant);		
			return true ;
		}
		return false ;
	}

	@Override
	public boolean updateApplicant(int idApplicant, Applicant A) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStateApplicant(ApplicantState ApplicantState) {
		// TODO Auto-generated method stub
		return false;
	}

}
