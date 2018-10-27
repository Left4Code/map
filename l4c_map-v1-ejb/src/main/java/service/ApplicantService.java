package service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import entities.Applicant;
import entities.Demand;
import entities.File;
import entities.User;
import enumerator.ApplicantState;
import enumerator.DemandState;

@Stateless
public class ApplicantService implements ApplicantServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;
	
	@Override
	public int insertApplicant(Applicant A) {
		A.setApplicantState(ApplicantState.Waiting);
		if(ApplicantState.valueOf(A.getApplicantState().toString()) instanceof ApplicantState 
				&& A.getAge()>=18 && !A.getName().equals("")
				&& !A.getLastname().equals("") && !A.getCountry().equals("")
				&& A!=null){
			File file = new File(Date.valueOf(LocalDate.now()),"");
			FileService fileservice = new FileService();
			Demand demand = new Demand(Date.valueOf(LocalDate.now()), DemandState.Waiting, "", file);
			DemandService demandservice = new DemandService();
			A.setDemand(demand);
			em.persist(A);
			return A.getId();
		}
		return -1;
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
		if(A.getApplicantState() == null)
			A.setApplicantState(ApplicantState.Waiting);
		if(applicant != null && A.getApplicantState() instanceof ApplicantState
				&& A.getAge()>=18 && !A.getName().equals("")
				&& !A.getLastname().equals("") && !A.getCountry().equals("")){
			applicant = A ;
			em.merge(applicant);
			return true ;
		}
		return false;
	}

	@Override
	public ArrayList<Applicant> getAllApplicant() {
		TypedQuery<Applicant> query = em.
				createQuery("SELECT a FROM Applicant a",Applicant.class);
		return (ArrayList<Applicant>) query.getResultList();
	}

	@Override
	public Applicant getApplicantById(int idApplicant) {
		Applicant applicant = em.find(Applicant.class, idApplicant);
		return applicant;
	}

	@Override
	public ArrayList<User> getApplicantByName(String name) {
		try{
		TypedQuery<User> query = em.
				createQuery("SELECT a FROM User a WHERE a.name=:name",User.class);
		return (ArrayList<User>) query.setParameter("name", name).getResultList();
		}catch(NoResultException e){
			
		}
		return null ;
	}

	@Override
	public ArrayList<Applicant> getApplicantByState(ApplicantState state) {
		TypedQuery<Applicant> query = em.
				createQuery("SELECT a FROM Applicant a WHERE a.applicantState=:applicantState",Applicant.class);
		return (ArrayList<Applicant>) query.setParameter("applicantState", state).getResultList();
	}

}
