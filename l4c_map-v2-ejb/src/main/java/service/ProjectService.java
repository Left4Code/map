package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Client;
import entities.Mandate;
import entities.Profitability;
import entities.Project;
import entities.Ressource;
import entities.Skills;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService implements ProjectServiceLocal {
	@PersistenceContext(unitName = "l4c_map-v2-ejb")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProjectService() {

	}

	@Override
	public void ajouterProjet(Project pr, int id) {
		Client client = em.find(Client.class, id);

		pr.setClient(client);
		em.persist(pr);

	}

	@Override
	public Boolean supprimerProjet(int idProject) {
		Project project = em.find(Project.class, idProject);
		if (project != null) {
			em.remove(project);
			return true;
		}
		return false;

	}

	@Override
	public void modifierProjet(Project pr) {
		Project project = em.find(Project.class, pr.getIdProject());

		project = pr;
		em.merge(project);

	}

	@Override
	public Project getProjetById(int idProjet) {

		TypedQuery<Project> query = em.createQuery("SELECT pr FROM Project pr where pr.idProject=:id", Project.class);
		return query.setParameter("id", idProjet).getSingleResult();
	}

	@Override
	public List<Project> getAllProjets() {

		TypedQuery<Project> query = em.createQuery("SELECT pr FROM Project pr", Project.class);
		return query.getResultList();
	}

	@Override
	public void getSkillsBySpeciality(int id, int idSkills) {

		TypedQuery<Project> query = em.createQuery("SELECT pr FROM Project pr where pr.idProject=:id", Project.class);
		Project project = query.setParameter("id", id).getSingleResult();

		TypedQuery<Skills> query1 = em.createQuery("SELECT s FROM Skills s where s.idSkills=:id", Skills.class);
		Skills s = query1.setParameter("id", idSkills).getSingleResult();

		Set<Skills> listeSk = project.getSkills();

		listeSk.add(s);

		project.setSkills(listeSk);
	}

	@Override
	public void CalculerRentability(int idProject) {
		TypedQuery<Mandate> query = em.createQuery("SELECT  m  FROM Mandate m ", Mandate.class);
		List<Mandate> ListeMa = query.getResultList();
		TypedQuery<Profitability> query1 = em.createQuery("SELECT pr FROM Profitability pr ", Profitability.class);
		List<Profitability> prof1 = query1.getResultList();
		Profitability prof = new Profitability();
		Project pr = em.find(Project.class, idProject);
		float cost = 0;

		if (prof1.isEmpty()) {
			for (Mandate m : ListeMa) {
				if (m.getMandatepk().getIdProject() == idProject) {
					cost = cost + m.getCost();
					System.out.println("condition 1");

					prof.setGain(cost);
					float lost = (float) (cost / 1.8);
					prof.setLost(lost);
					prof.setProfitability(cost - lost);
					prof.setProject(pr);

				}

			}

			em.persist(prof);
		}
		for (Profitability p : prof1) 
			if (p.getProject().getIdProject()!=idProject&&p!=null) {
				for (Mandate m : ListeMa) {
					if (m.getMandatepk().getIdProject() == idProject) {
						cost = cost + m.getCost();
						System.out.println("ckjjjjondition 2");

						prof.setGain(cost);
						float lost = (float) (cost / 1.8);
						prof.setLost(lost);
						prof.setProfitability(cost - lost);
						prof.setProject(pr);

					}

				}

				em.persist(prof);
			
		}
		for (Profitability p : prof1) {
			 if (p.getProject().getIdProject() ==idProject) {

				for (Mandate m : ListeMa) {
					if (m.getMandatepk().getIdProject() == idProject) {
						Profitability p2 = new Profitability();
						Profitability p1 = em.find(Profitability.class, p.getIdProfitability());
						cost = cost + m.getCost();
						System.out.println("condition 3");
						p1.setGain(cost);
						float lost = (float) (cost / 1.8);
						p1.setLost(lost);
						p1.setProfitability(cost - lost);
						
						
					}

				}

			}
		}
	}

	@Override
	public List<Profitability> getAllProfitablity() {
		TypedQuery<Profitability> query = em.createQuery("SELECT  p  FROM Profitability p ", Profitability.class);
		return query.getResultList();

	}

	@Override
	public List<Skills> afficherSkills(int idProject) {
		System.out.println("ena sk0");

		TypedQuery<Skills> query = em.createQuery("Select c from Skills c", Skills.class);
		List<Skills> rr = query.getResultList();
		System.out.println("liste :" + rr.toString());
		List<Skills> listSk = new ArrayList<>();
		TypedQuery<Project> query1 = em.createQuery("SELECT pr FROM Project pr where pr.idProject=:id", Project.class);
		query1.setParameter("id", idProject);
		Project pr = query1.getSingleResult();
		System.out.println("project :" + pr.toString());
		if (pr.getSkills().isEmpty()) {

			listSk = rr;

		} else {
			for (Skills sk : pr.getSkills()) {
				for (Skills s : rr) {

					if (s.getIdSkills() != sk.getIdSkills()) {
						System.out.println("ena sk2" + sk);
						listSk.add(s);
					}

				}
			}
		}

		System.out.println(listSk);
		return listSk;
	}
}
