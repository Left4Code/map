package service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Applicant;
import entities.Demand;
import entities.File;
import entities.User;

@Stateless
public class DemandService implements DemandServiceLocal {
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public int insertDemand(Demand demand, int idApplicant) {
		try {
			TypedQuery<Applicant> query = em.createQuery("SELECT a FROM Applicant a WHERE a.id=:id", Applicant.class);
			Applicant applicantExist = (Applicant) query.setParameter("id", idApplicant).getSingleResult();
			if (applicantExist.getDemand() == null) {
				File file = new File(Date.valueOf(LocalDate.now()),"");
				demand.setFile(file);
				applicantExist.setDemand(demand);
				em.persist(demand);
				return demand.getIdDemand();
			}
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	public ArrayList<Demand> getAllDemand() {
		TypedQuery<Demand> query = em.createQuery("SELECT d FROM Demand d", Demand.class);
		return (ArrayList<Demand>) query.getResultList();
	}

	@Override
	public boolean updateDemand(Demand demand) {
		demand.setDateDemand(Date.valueOf(LocalDate.now()));
		Demand demandExist = em.find(Demand.class, demand.getIdDemand());
		if (demandExist != null) {
			demandExist = demand;
			em.merge(demandExist);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeDemand(int idDemand) {
		try {
			Demand demand = em.find(Demand.class, idDemand);
			TypedQuery<Applicant> query = em.createQuery("SELECT a FROM Applicant a WHERE a.demand=:demand",
					Applicant.class);
			Applicant applicant = query.setParameter("demand", demand).getSingleResult();
			if (demand != null) {
				applicant.setDemand(null);
				em.remove(demand);
				return true;
			}
		} catch (NoResultException e) {
		}
		return false;
	}

}
