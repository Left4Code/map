package Services;

import java.awt.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Ressource;
import entities.Skills;

/**
 * Session Bean implementation class RessourcesServices
 */
@Stateless
@LocalBean
public class RessourcesServices implements RessourcesServicesRemote, RessourcesServicesLocal {

    /**
     * Default constructor. 
     */
    public RessourcesServices() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em ; 
	
    /**
     * Default constructor. 
     */

	@Override
	public void ajouterRessources(Ressource ressource) {
		em.persist(ressource);
		System.out.println("ressource ajoutée");
	}



	@Override
	public void modifierRessources(Ressource ressource) {
		Ressource r =new Ressource();
		r=ressource ;
		em.merge(r);
		System.out.println("ressource modifiée avec succés");
		
	}

	@Override
	public void supprimerRessources(int idRessources) {
		em.remove(em.find(Ressource.class, idRessources));
		System.out.println("ressource supprimée avec succés");

	}

	@Override
	public void afficherRessources(int idRessource) {
		em.find(Ressource.class, idRessource);
		
	}

	@Override
	public void afficherTousLesRessources() {
		TypedQuery<Ressource> query = em.createQuery("Select r from Ressource c",Ressource.class);
		List<Ressource> r = query.getResultList();
		System.out.println(r);
	}

	@Override
	public void affecterRessourceAunProjet(int idRessource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modiffierAffectationRessourceAunProjet(int idRessource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterCompetence(int idRessource, Skills skill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierCompetence(int idRessource, Skills skill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerCompetence(int idRessource, Skills skill) {
		// TODO Auto-generated method stub
		
	}
	
}
