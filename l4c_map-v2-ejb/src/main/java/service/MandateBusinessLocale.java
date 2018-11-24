package service;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.Mandate;
import entities.Project;
import entities.Ressource;

@Local
public interface MandateBusinessLocale {
	
	public void addMandate (Mandate m);
	public void deleteMandate(Mandate m);
	public void updateMandate(Mandate m);
	public List<Mandate> getMandates();
	public List<Mandate> getCurrentMandate();
	public List<Mandate> getMandateByProject(int p);
	public List<Mandate> getMandateByResources(int r);
	public List<Mandate> getEndingMandates();
	public List<Mandate> getArchivedMandate();
	public List <Mandate> getArchivedMandateByProject(int p);
	public List<Mandate> getArchivedMandateByRessource(int r);
	public float fraisMandat(Mandate m);
	public void stockMandate();
	
	public List<Ressource> getRessources(int p);
}
