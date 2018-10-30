package services;

import java.util.List;

import javax.ejb.Local;

import entities.Client;
import entities.Project;
import entities.Ressource;
import entities.Skills;

@Local
public interface ProjectServiceLocal {
	public void ajouterProjet (Project pr,int idClient);
	public Boolean supprimerProjet (int idProject);
	public void modifierProjet (Project pr,int idClient);
	public Project getProjetById(int idProjet);
	public List<Project> getAllProjets();
	public List<Skills> getSkillsBySpeciality(int idProject);
	public void affecterSkills(int idProject);
}
