package service;

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
	public void getSkillsBySpeciality(int id);
    public void CalculerRentability(int idProject);
}
