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
import entities.Ressource;
import entities.Skills;

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
	public void ajouterProjet(Project pr,int idClient) {
		Client client=em.find(Client.class,idClient);
	
		pr.setClient(client);
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
	public void modifierProjet(Project pr,int idClient) {
		Project project=em.find(Project.class,pr.getIdProject());
		
		Client client=em.find(Client.class,idClient);

		pr.setClient(client);
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
	
	
	@Override
	public List<Skills>  getSkillsBySpeciality(int idProject)
	{
		Project project=em.find(Project.class,idProject);
	
		List<Skills> listeSk=new ArrayList();
		TypedQuery<Skills> query=em.createQuery("SELECT  s  FROM Skills s  ",Skills.class);
		List<Skills> listeSkills= query.getResultList();

		TypedQuery<Project> query1=em.createQuery("SELECT  p  FROM Project p  ",Project.class);
		List<Project> listeProjects= query1.getResultList();
		
		
	for(Project pr : listeProjects){
		for(Skills sk: listeSkills )
		{
			if(sk.getSpecialty().equals(project.getTypeProject())){
				
				listeSk.add(sk);
				
				
			}
			
			
			
		}
	}
	return listeSk;

	}

	@Override
	public void affecterSkills(int idProject) {
		List<Skills> listesk=new ArrayList<>();
	listesk=getSkillsBySpeciality(idProject);
	
			
			Project pr=em.find(Project.class,idProject);
			
			pr.setListeSkills(listesk);
			
		
	}
}
