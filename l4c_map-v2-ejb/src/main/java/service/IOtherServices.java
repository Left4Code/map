package service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface IOtherServices {
	public List<Object> getEmployeVsPigiste();
	public List<Object> getMandateVsInterMandate();
	
}
