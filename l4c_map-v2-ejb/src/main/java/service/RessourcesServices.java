package service;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.rmi.CORBA.UtilDelegate;

import entities.Demand_time_off;
import entities.Mandate;
import entities.Project;
import entities.Responsable;
import entities.Ressource;
import entities.Skills;
import enumerator.DemandState;

/**
 * Session Bean implementation class RessourcesServices
 */
@Stateless
@LocalBean
public class RessourcesServices implements RessourcesServicesLocal {

	/**
	 * Default constructor.
	 */
	public RessourcesServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "l4c_map-v2-ejb")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	// start of ressources cruds .
	@Override
	public void ajouterRessources(Ressource ressource) {
		em.persist(ressource);
		System.out.println("ressource ajoutée");

	}

	@Override
	public void modifierRessources(Ressource ressource) {
		Ressource r = em.find(Ressource.class, ressource.getId());
		r = ressource;
		em.merge(r);
		System.out.println("ressource modifiée avec succés");

	}

	@Override
	public void supprimerRessources(int idRessources) {
		em.remove(em.find(Ressource.class, idRessources));
		System.out.println("ressource supprimée avec succés");

	}

	@Override
	public Ressource afficherRessources(int idRessource) {
		Ressource ressource = em.find(Ressource.class, idRessource);

//		 try {
//		 System.out.println("d5alna");
//		 FirstPdf fs = new FirstPdf();
//		 System.out.println("d5alna2");
//		
//		 fs.SetInfos(ressource.getId(), ressource.getName(),
//		 ressource.getLastname(),
//		 ressource.getSpecialty(),ressource.getBusinessSector(),
//		 ressource.getRateSelling(), ressource.getCost(),
//		 (ressource.getTypeContrat()).toString(),
//		 ressource.getSeniority(), ressource.getNote() );
//		 System.out.println("d5alna22");
//		
//		 fs.createPDF();
//		 System.out.println("d5alna23");
//		
//		 } catch (IOException e) {
//		 System.out.println("5rajna");
//		 // TODO Auto-generated catch block
//		 System.out.println("ahay el erreure : "+e);}
//		 Runnable r = new MyR(ressource);
//		 new Thread(r).start();

		String etat = "";
		boolean hasMandate = ressource.getListemandate().iterator().hasNext();
		boolean hasTimeOff = ressource.getListeDemandesTimeOff().iterator().hasNext();

		Date now = new Date(Calendar.getInstance().getTimeInMillis());

		if (hasMandate == true) {
			Mandate mn = ressource.getListemandate().iterator().next();

			int difference = (int) ((mn.getDateEnd().getTime() - now.getTime()) / (1000 * 60 * 60 * 24)) + 1;

			if (difference <= 14 && difference > 0) {
				etat = ("Available in : " + difference + " days.");
			} else if (difference > 14) {
				etat = ("Not available");
			} else {
				etat = ("Available");

			}

		} else if (hasMandate == false && hasTimeOff == true) {
			Demand_time_off demand = ressource.getListeDemandesTimeOff().iterator().next();
			Date debut = demand.getDateBegin();
			Date fin = demand.getDateEnd();
			int diffCongeNow = (int) ((fin.getTime() - now.getTime()) / (1000 * 60 * 60 * 24)) + 1;
			int diffNowConge = (int) ((now.getTime() - debut.getTime()) / (1000 * 60 * 60 * 24)) + 1;

			if (diffCongeNow > 0 && diffNowConge > 0) {
				etat = ("Available in : " + diffCongeNow + " days .");

			} else if (diffCongeNow < 0 && diffNowConge > 0) {
				etat = ("Available");
			}

		} else if (hasMandate == false && hasTimeOff == false) {
			etat = ("Available");
		}
		System.out.println(etat);
		ressource.setCv(etat);	
		affecterNoteARessource(ressource.getId());
		return ressource;

	}

	@Override
	public List<Ressource> afficherTousLesRessources() {
		TypedQuery<Ressource> query = em.createQuery("Select c from Ressource c", Ressource.class);
		List<Ressource> r = query.getResultList();
		r.forEach(e -> {
			String etat = "";
			Ressource ressource = em.find(Ressource.class, e.getId());
			boolean hasMandate = ressource.getListemandate().iterator().hasNext();
			boolean hasTimeOff = ressource.getListeDemandesTimeOff().iterator().hasNext();

			Date now = new Date(Calendar.getInstance().getTimeInMillis());
			System.out.println(now);

			if (hasMandate == true) {
				Mandate mn = ressource.getListemandate().iterator().next();

				int difference = (int) ((mn.getDateEnd().getTime() - now.getTime()) / (1000 * 60 * 60 * 24)) + 1;

				if (difference <= 14 && difference > 0) {
					etat = ("Available in : " + difference + " days.");
				} else if (difference > 14) {
					etat = ("Not Available");
				} else {
					etat = ("Available");

				}

			} else if (hasMandate == false && hasTimeOff == true) {
				Demand_time_off demand = ressource.getListeDemandesTimeOff().iterator().next();
				Date debut = demand.getDateBegin();
				Date fin = demand.getDateEnd();
				int diffCongeNow = (int) ((fin.getTime() - now.getTime()) / (1000 * 60 * 60 * 24)) + 1;
				int diffNowConge = (int) ((now.getTime() - debut.getTime()) / (1000 * 60 * 60 * 24)) + 1;

				if (diffCongeNow > 0 && diffNowConge > 0) {
					etat = ("Available in : " + diffCongeNow + " days .");

				} else if (diffCongeNow < 0 && diffNowConge > 0) {
					etat = ("Available");
				}else if (diffCongeNow <  0){
					etat = ("Available");

				}

			} else if (hasMandate == false && hasTimeOff == false) {
				etat = ("Available");
			}
			System.out.println(etat);
			e.setCv(etat);
			System.out.println("not getAll");
			affecterNoteARessource(e.getId());

		});
		return r;

	}
	

	@Override
	public Ressource trouverRessourcepar(String name, String lastName) {
		TypedQuery<Ressource> query = em.createQuery("Select c from Ressource c where c.name=:name and c.lastname=:lastname",Ressource.class);
		query.setParameter("name", name);
		query.setParameter("lastname", lastName);
		return query.getSingleResult();

	}

	@Override
	public long nombreSkills() {
		String sql = "SELECT COUNT(d.idSkills) FROM Skills d";
		Query q = em.createQuery(sql);
		long count = (long)q.getSingleResult();
		return count ;
	}

	@Override
	public void affecterNoteARessource(int idRessource) {
		Ressource r = em.find(Ressource.class, idRessource);
		int seniority = r.getSeniority();
		int numberSkills = r.getSkills().size();
		System.out.println("skill nbr : "+numberSkills);
		int numberMandates = r.getListemandate().size();
		int cote = 1;
		if (seniority < 3) {// junior
			if (numberSkills <=  2) {
				cote = cote * 2;
			} else if (numberSkills > 2) {
				cote = cote * 3;

			}
		} else if (seniority >= 3 && seniority < 6) {// experienced
			if (numberSkills <= 2) {
				cote = cote * 3;
			} else if (numberSkills > 2) {
				cote = cote * 4;

			}
		} else if (seniority >= 6) {// senior
			if (numberSkills <= 2) {
				cote = cote * 4;
			} else if (numberSkills > 2) {
				cote = cote * 5;

			}
		}
		
		System.out.println("couta"+cote);
		if (cote > 1)
			r.setNote(cote);
		else
			r.setNote(1);
		System.out.println("note affectée : "+r.getNote());
		em.merge(r);

	}
	// end of ressources cruds .

	// start of skills cruds .
	@Override
	public void ajouterCompetence(Skills skill) {
		em.persist(skill);
		System.out.println("ajoutée");
	}

	@Override
	public void ajouterCompetenceARessource(int idRessource, int idSkill) {
		Skills s = em.find(Skills.class, idSkill);
		Ressource rc = em.find(Ressource.class, idRessource);
		Set<Skills> skills = rc.getSkills();
		skills.add(s);
		rc.setSkills(skills);
		affecterNoteARessource(idRessource);

	}

	@Override
	public void modifierCompetence(Skills skill) {
		Skills sk = em.find(Skills.class, skill.getIdSkills());
		sk = skill;
		em.merge(sk);
		System.out.println("Skill modifiée avec succés");

	}

	@Override
	public void supprimerCompetence(int idSKill) {
		TypedQuery<Ressource> q = em.createQuery("Select c from Ressource c", Ressource.class);
		List<Ressource> ListR = q.getResultList();
		TypedQuery<Skills> q1 = em.createQuery("Select s from Skills s", Skills.class);
		List<Skills> ListS = q1.getResultList();
		int idRessource = 0;
		for (Ressource r : ListR) {
			if (r.getSkills().contains(em.find(Skills.class, idSKill))) {
				idRessource = r.getId();
			} else {
				idRessource = 0;
			}
		}

		if (idRessource != 0) {
			Ressource ressource = em.find(Ressource.class, idRessource);

			Set<Skills> skk = ressource.getSkills();
			skk.remove(em.find(Skills.class, idSKill));
			ressource.setSkills(skk);
			em.remove(em.find(Skills.class, idSKill));

			affecterNoteARessource(em.find(Ressource.class, idRessource).getId());
		} else
			System.out.println("impossible");

	}

	@Override
	public void ajouterRessourceEtCompetence(int idRessource, Skills skill) {
		Ressource rc = em.find(Ressource.class, idRessource);
		Skills sk = skill;
		Set<Skills> skills = rc.getSkills();
		skills.add(sk);
		rc.setSkills(skills);
		em.persist(sk);
		affecterNoteARessource(idRessource);

	}

	@Override
	public List<Skills> afficherSkills() {
		TypedQuery<Skills> query = em.createQuery("Select c from Skills c", Skills.class);
		List<Skills> rr = query.getResultList();
		return rr;
	}

	// end of skills cruds .

	// start of timeOff cruds .
	@Override
	public void ajouterDemandeConge(int idRessource, Demand_time_off demande) {
		Ressource r = em.find(Ressource.class, idRessource);
		if (r != null) {
			Demand_time_off dm = demande;
			Date debut = demande.getDateBegin();
			System.out.println(debut);
			Date fin = demande.getDateEnd();
			System.out.println(fin);

			int diffInDays = (int) ((fin.getTime() - debut.getTime()) / (1000 * 60 * 60 * 24)) + 1;
			if (diffInDays > 0) {
				dm.setDuration(diffInDays);
				dm.setRessource(r);
				em.persist(dm);
			}
		} else
			System.out.println("ressource introuvable , congé non ajouté");
	}

	
	@Override
	public List<Demand_time_off> afficherDemandesCongesPourRessource(int id) {
		TypedQuery<Demand_time_off> query = em.createQuery("Select c from Demand_time_off c where c.ressource.id=:id", Demand_time_off.class);
		query.setParameter("id", id);
		List<Demand_time_off> ls = query.getResultList();
		return ls;
	}

	@Override
	public List<Demand_time_off> afficherDemandesConges() {
		TypedQuery<Demand_time_off> query = em.createQuery("Select c from Demand_time_off c", Demand_time_off.class);
		List<Demand_time_off> ls = query.getResultList();
		return ls;
	}

	@Override
	public Demand_time_off afficherUneDemandeConge(int idDemande) {
		Demand_time_off dm = em.find(Demand_time_off.class, idDemande);
		return dm;
	}

	@Override
	public void modifierDemandeConge(Demand_time_off demande) {
		Date debut = demande.getDateBegin();
		Date fin = demande.getDateEnd();
		int diffInDays = (int) ((fin.getTime() - debut.getTime()) / (1000 * 60 * 60 * 24)) + 1;
		if (diffInDays > 0) {
			Demand_time_off dem = em.find(Demand_time_off.class, demande.getIdDemandTimeOff());
			dem.setDateBegin(debut);
			dem.setDateEnd(fin);
			dem.setDuration(diffInDays);
		} else
			System.out.println("failed to modify");

	}

	@Override
	public void modifierEtatDemandeCongeParResponsable(int idResponsable, Demand_time_off demande) {
		Demand_time_off dem = em.find(Demand_time_off.class, demande.getIdDemandTimeOff());
		dem.setStateDemandTimeOff(demande.getStateDemandTimeOff());
		dem.setResponsable(em.find(Responsable.class, idResponsable));

	}
	
	

	// end of time off cruds .

	// public static class MyR implements Runnable {
		//
		// private Ressource ressource ;
		//
		// public MyR(Ressource ressource ) {
		// this.ressource = ressource;
		// }
		//
		// @Override
		// public void run() {
		//
		//
		//
		// if(ressource!=null){
		// System.out.println("haw ressource : " + ressource.toString());
		//
		// try { System.out.println("ena hne 1 ");
		//
		// FirstPdf fs = new FirstPdf();
		// fs.SetInfos(ressource.getId(), ressource.getName(),
		// ressource.getLastname(), ressource.getSpecialty(),
		// ressource.getBusinessSector(), ressource.getRateSelling(),
		// ressource.getCost(),
		// (ressource.getTypeContrat()).toString(), ressource.getSeniority(),
		// ressource.getNote());
		// fs.createPDF();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// System.out.println("ena hne");
		// e.printStackTrace();
		// }
		// }else
		// System.out.println("no");
		// return;
		// }
		// }


	@Override
	public void supprimerSkills(int id) {
		em.remove(em.find(Demand_time_off.class, id));
	}

	public static void main(String args[]) {

		
	}
}
