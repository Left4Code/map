package service;

import java.util.List;

import javax.ejb.Local;

import entities.HistoricalMandate;

@Local
public interface HistoricalMandateBusinessLocal {
	
	public void addHistoricalMandate();
	public List<HistoricalMandate> getHistoricalMandate();
}
