package BusinessLayer;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import entities.Mandate;
@Local
public interface IBusinessReports {

	public void testDate();
	public List<Mandate> AddActivityReport(int resourceID);
	
}
