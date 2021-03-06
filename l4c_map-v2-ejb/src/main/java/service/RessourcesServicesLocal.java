package service;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entities.Demand_time_off;
import entities.Ressource;
import entities.Skills;

@Local
public interface RessourcesServicesLocal {
	void ajouterRessources(Ressource ressource);
	void modifierRessources(Ressource ressource);
	void supprimerRessources(int idRessources);
	Ressource afficherRessources(int idRessource);
	List<Ressource> afficherTousLesRessources();
	void ajouterCompetenceARessource(int idRessource , int idSKill);
	void ajouterCompetence(Skills skill);
	void modifierCompetence(Skills skill );
	void supprimerCompetence(int idSKill );
	List<Skills> afficherSkills();
	void ajouterDemandeConge(int idRessource , Demand_time_off demande);
	List<Demand_time_off> afficherDemandesConges();
	List<Demand_time_off> afficherDemandesCongesPourRessource(int id);

	Demand_time_off afficherUneDemandeConge(int idDemande);
	void ajouterRessourceEtCompetence(int idRessource , Skills skill );
	void modifierDemandeConge( Demand_time_off demande);
	void modifierEtatDemandeCongeParResponsable(int idResponsable ,Demand_time_off demande);
	void affecterNoteARessource(int idRessource);
	Ressource trouverRessourcepar(String name , String lastName);
	long nombreSkills();
	void supprimerSkills(int id );
}
