package service;

import java.util.ArrayList;
import java.util.List;

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
	@PersistenceContext(unitName="l4c_map-v2-ejb")
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
	public void  getSkillsBySpeciality(int id)
	{
	
		List<Skills> listeSk=new ArrayList();
		TypedQuery<Skills> query=em.createQuery("SELECT  s  FROM Skills s  ",Skills.class);
		List<Skills> listeSkills= query.getResultList();

		Project project=em.find(Project.class,id);
		
		

		for(Skills sk: listeSkills )
		{
			if(sk.getSpecialty().equals(project.getTypeProject().toString())){
				
				listeSk.add(sk);
				
				
			}
			
			
			
		
	}
	
		project.setListeSkills(listeSk);
	}

	@Override
	public void CalculerRentability(int idProject) {
		TypedQuery<Mandate> query=em.createQuery("SELECT  m  FROM Mandate m ",Mandate.class);
		List<Mandate> ListeMa= query.getResultList();
		
	Profitability prof=new Profitability();	
Project pr=em.find(Project.class,idProject);
float cost=0;
for(Mandate m: ListeMa )
{
	if(m.getMandatepk().getIdProject()==idProject){
 cost=cost+m.getCost();
		

prof.setGain(cost);
float lost=(float) (cost/1.8);
prof.setLost(lost);
prof.setProfitability(cost-lost);
prof.setProject(pr);
		
	}
	
	
		
	}

em.persist(prof);

	}
}
