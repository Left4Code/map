package Services;

import javax.ejb.Local;

import entities.Ressource;
import entities.Skills;

@Local
public interface RessourcesServicesLocal {
	void ajouterRessources(Ressource ressource);
	void modifierRessources(Ressource ressource);
	void supprimerRessources(int idRessources);
	void afficherRessources(int idRessource);
	void afficherTousLesRessources();
	void affecterRessourceAunProjet(int idRessource);
	void modiffierAffectationRessourceAunProjet(int idRessource);
	void ajouterCompetence(int idRessource , Skills skill );
	void modifierCompetence(int idRessource , Skills skill );
	void supprimerCompetence(int idRessource , Skills skill );
}
