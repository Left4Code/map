package services;

import java.util.List;

import javax.ejb.Local;

import entities.Client;
import entities.Project;

@Local
public interface ProjectServiceLocal {
	public void ajouterProjet (Project pr);
	public Boolean supprimerProjet (int idProject);
	public void modifierProjet (Project pr);
	public Project getProjetById(int idProjet);
	public List<Project> getAllProjets();
}
