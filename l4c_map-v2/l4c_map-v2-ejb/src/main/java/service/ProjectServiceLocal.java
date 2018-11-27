package service;

import java.util.List;

import javax.ejb.Local;

import entities.Client;
import entities.Profitability;
import entities.Project;
import entities.Ressource;
import entities.Skills;

@Local
public interface ProjectServiceLocal {
	public void ajouterProjet (Project pr,int id);
	public Boolean supprimerProjet (int idProject);
	public void modifierProjet (Project pr);
	public Project getProjetById(int idProjet);
	public List<Project> getAllProjets();
	public void getSkillsBySpeciality(int id,int idSkills);
    public void CalculerRentability(int idProject);
	public List<Profitability> getAllProfitablity();
	public List<Skills> afficherSkills(int idProject) ;

}
