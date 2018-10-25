package service;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import entities.Demand;

@Local
public interface DemandServiceLocal {
	public int insertDemand(Demand demand) ;
}
