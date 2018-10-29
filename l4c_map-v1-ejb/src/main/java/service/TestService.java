package service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Applicant;
import entities.Demand;
import entities.File;
import entities.Responsable;
import entities.Test;

@Stateless
public class TestService implements TestServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public int insertTest(Test test,int idResponsable) {
		Responsable responsable = em.find(Responsable.class, idResponsable);
		if(responsable != null){
			test.setResponsable(responsable);
			em.persist(test);
			return test.getIdTest() ;
		}
		return 0 ;
	}

	@Override
	public boolean updateTest(Test test) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTest(Test test) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int makeTest(Test test, int idApplicant) {
		Test testExist = em.find(Test.class, test.getIdTest());
		testExist = test ;
		TypedQuery<Applicant> query = em.createQuery("SELECT a FROM Applicant a WHERE a.id=:id", Applicant.class);
		Applicant applicant = (Applicant) query.setParameter("id",idApplicant).getSingleResult();
		Demand demand = em.find(Demand.class, applicant.getDemand().getIdDemand());
		File file = em.find(File.class, demand.getFile().getId());
		if(testExist != null && applicant != null){
			testExist.setDateOfPassing(Date.valueOf(LocalDate.now()));
			Set<Test> listetest = file.getListeTest();
			listetest.add(testExist);
			file.setListeTest(listetest);
			new ApplicantService().ChanceCalculator(applicant,em);
			return testExist.getMark();
		}
		return 0;
	}
}
