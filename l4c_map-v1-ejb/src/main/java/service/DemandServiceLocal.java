package service;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import entities.Demand;

@Local
public interface DemandServiceLocal {
	public int insertDemand(Demand demand,int idApplicant) ;
	public ArrayList<Demand> getAllDemand();
	public boolean updateDemand(Demand demand);
	public boolean removeDemand(int idDemand);
}
