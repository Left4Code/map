package service;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import entities.Mandate;

@Local
public interface IActivityReportBusiness {

	public List<Mandate> oneResActivities(int idRes,String from,String to);
	public float rateActivtyOneRes(int idRes,String from,String to);
	public float satisfactionMsgRatio(int resID, String dateFrom, String dateTo);
	

}
