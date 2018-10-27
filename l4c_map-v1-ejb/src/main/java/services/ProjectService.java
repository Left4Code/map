package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Client;
import entities.Project;

/**
 * Session Bean implementation class ProjectService
 */
@Stateless
@LocalBean
public class ProjectService implements ProjectServiceLocal {
	@PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em;
	

    /**
     * Default constructor. 
     */
    public ProjectService() {
      
    }

	@Override
	public void ajouterProjet(Project pr) {
		em.persist(pr);
		
	}

	@Override
	public Boolean supprimerProjet(int idProject) {
	Project project= em.find(Project.class,idProject);
	if(project != null){
		em.remove(project);		
		return true ;
	}
	return false ;

	}

	@Override
	public void modifierProjet(Project pr) {
		Project project=em.find(Project.class,pr.getIdProject());
		project=pr;
		em.merge(project);
	}

	@Override
	public Project getProjetById(int idProjet) {

		TypedQuery<Project> query=em.createQuery("SELECT pr FROM Project pr where pr.idProject=:id",Project.class);
		return query.setParameter("id",idProjet).getSingleResult();
	}
	
	
	@Override
	public List<Project> getAllProjets() {

		TypedQuery<Project> query=em.createQuery("SELECT pr FROM Project pr",Project.class);
		return query.getResultList();
	}
	
	

}
