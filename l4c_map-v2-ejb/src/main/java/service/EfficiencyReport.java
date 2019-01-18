package service;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Ressource;

@Stateless
public class EfficiencyReport implements IEffeciency{
	
	@PersistenceContext(unitName="l4c_map-v2-ejb")
	EntityManager em;

	
	@EJB
	IActivityReportBusiness IARB;
	
	@Override
	public List<List<Object>> efficiencyReport(Integer resID) {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(0);
		formatter.setRoundingMode(RoundingMode.HALF_UP); 
		List<Ressource> res =new ArrayList<>();
		
		List<List<Object>> l2 =new ArrayList<>();
		
		float rAOR=0;
		float sMR=0;
		float avg=0;
		if(resID!=null){
			List<Object> m =new ArrayList<>();
			
			rAOR=IARB.rateActivtyOneRes(resID, null, null);
			sMR=IARB.satisfactionMsgRatio(resID, null, null);
			avg=(rAOR+sMR)/2;
			Ressource R1=em.find(Ressource.class, resID);
			String rentaStr="rentabilité :"+new Float(formatter.format(avg))+"%";
			m.add(R1);
			m.add(rentaStr);
			l2.add(m);
			return l2;
			
		}
		else{
		

		TypedQuery<Ressource> allRessources=em.createQuery("select r from Ressource r ",Ressource.class);
		res =allRessources.getResultList();
		for (Ressource r : res){
			List<Object> l =new ArrayList<>();
			
			rAOR=IARB.rateActivtyOneRes(r.getId(), null, null);
			sMR=IARB.satisfactionMsgRatio(r.getId(), null, null);
			avg=(rAOR+sMR)/2;
			
			String rentaStr="rentabilité :"+new Float(formatter.format(avg))+"%";
			l.add(r);
			l.add(rentaStr);
			l2.add(l);
		}
		return l2;
		}
	}

}
