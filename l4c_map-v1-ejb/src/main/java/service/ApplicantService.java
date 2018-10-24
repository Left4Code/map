package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		Applicant applicant = em.find(Applicant.class, idApplicant);
		if(applicant != null){
			applicant = A ;
			em.merge(applicant);
			return true ;
		}
		return false;
	}

	@Override
	public ArrayList<Applicant> getAllApplicant() {
		TypedQuery<Applicant> query = em.createQuery("SELECT a FROM Applicant a",Applicant.class);
		return (ArrayList<Applicant>) query.getResultList();
	}

	@Override
	public Applicant getApplicantById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getApplicantByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Applicant> getApplicantByState() {
		// TODO Auto-generated method stub
		return null;
	}

}
