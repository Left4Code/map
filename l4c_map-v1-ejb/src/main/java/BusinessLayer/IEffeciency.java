package BusinessLayer;

import java.util.List;

import javax.ejb.Local;

@Local
public interface IEffeciency {
	
public List<List<Object>> efficiencyReport(Integer resID);
}
