package BusinessLayer;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Mandate;
import entities.Ressource;
import enumerator.MessageType;
import enumerator.fromToDirection;
@Stateless
public class ActivityReportBusiness implements IActivityReportBusiness{
	@PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public List<Mandate> oneResActivities(int idRes, String f, String t) {
		List<Mandate> m=new ArrayList<>();
		Ressource R= em.find(Ressource.class, idRes);
		java.sql.Date to;
		java.sql.Date from;
		if(f==null&&t==null){
			java.util.Date date = new java.util.Date();
			
			if(R.getDateFin().compareTo(new java.sql.Date(date.getTime()))<0){
			to=new java.sql.Date(R.getDateFin().getTime());
			System.out.println(to);
			}
			else
			to=new java.sql.Date(date.getTime());
			System.out.println(to);
			from= new java.sql.Date(R.getDateDebut().getTime());
		}
		else
		{
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH);
		java.util.Date datef=new java.util.Date() ;
		java.util.Date datet=new java.util.Date() ;
		try {
			 datef = format.parse(f);
			 datet= format.parse(t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		from = new java.sql.Date(datef.getTime());
		to = new java.sql.Date(datet.getTime());
		}
		TypedQuery<Mandate> mandateQuery=em.createQuery("select m from Mandate m where m.ressource = :R AND ((m.dateBegin > :from AND m.dateBegin < :to ) OR (m.dateEnd > :from AND m.dateEnd< :to))",Mandate.class);
		mandateQuery.setParameter("R", R);
		mandateQuery.setParameter("from", from);
		mandateQuery.setParameter("to", to);
		List<Mandate> l =mandateQuery.getResultList();
				return l;
	}

	@Override
	public float rateActivtyOneRes(int idRes, String f, String t) {
		Ressource R= em.find(Ressource.class, idRes);
		java.sql.Date to;
		java.sql.Date from;
		if(f==null&&t==null){
			java.util.Date date = new java.util.Date();
			
			if(R.getDateFin().compareTo(new java.sql.Date(date.getTime()))<0){
			to=new java.sql.Date(R.getDateFin().getTime());
			System.out.println(to);
			}
			else
			to=new java.sql.Date(date.getTime());
			System.out.println(to);
			from= new java.sql.Date(R.getDateDebut().getTime());
		}
		else
		{
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH);
		java.util.Date datef=new java.util.Date() ;
		java.util.Date datet=new java.util.Date() ;
		try {
			 datef = format.parse(f);
			 datet= format.parse(t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		from = new java.sql.Date(datef.getTime());
		to = new java.sql.Date(datet.getTime());
		}
		List<Mandate>l =new ArrayList<>();
		l=oneResActivities(idRes, f, t);
		
		float dureeActiviteDemandeeInHours=0;
		float dureeMandateInHours =0;
		float rateActivity=0;
		
		int i=0;
		
		if(l.isEmpty()){
			if( ( (R.getDateDebut().compareTo(to))<0 &&(R.getDateDebut().compareTo(from)>0) ) ||( (R.getDateFin().compareTo(to)<0) && (R.getDateFin().compareTo(from)>0) ) ){
				System.out.println("0");
				return 0;
				}
			else{
				System.out.println("404");
				return 404;
				}
		}
		else{
		for (Mandate mand : l){
			if(mand.getDateBegin().compareTo(from)<0){
				dureeMandateInHours+=mand.getDateEnd().getTime()-from.getTime();
				i=1;
			}
				else if(mand.getDateEnd().compareTo(to)>0){
					dureeMandateInHours+= to.getTime()-mand.getDateBegin().getTime();
					i=2;
			}
				else{
					dureeMandateInHours+=mand.getDateEnd().getTime()-mand.getDateBegin().getTime();
					i=3;
				}
			System.out.println("/////////////////");
			System.out.println(i);
			System.out.println((dureeMandateInHours/3600000)/24);
			System.out.println("///////////////////");
			
		}
		dureeMandateInHours=dureeMandateInHours/3600000;
		if((R.getDateDebut().compareTo(from)>0)&&(R.getDateFin().compareTo(to)<0)){
			dureeActiviteDemandeeInHours=(R.getDateFin().getTime()-R.getDateDebut().getTime())/3600000;
		}
		else if((R.getDateDebut().compareTo(from)>0)&&(R.getDateFin().compareTo(to)>0))
			dureeActiviteDemandeeInHours=(to.getTime()-R.getDateDebut().getTime())/3600000;
		else if ((R.getDateDebut().compareTo(from)<0)&&(R.getDateFin().compareTo(to)<0))
			dureeActiviteDemandeeInHours=(R.getDateFin().getTime()-from.getTime())/3600000;
		else
			dureeActiviteDemandeeInHours=(to.getTime()-from.getTime())/3600000;
		
		
		System.out.println("time activity ------------------");
		System.out.println(dureeMandateInHours);
		System.out.println("time requested ------------------");
		System.out.println(dureeActiviteDemandeeInHours);
		System.out.println("my mandate liste---------------");
		//System.out.println(l);
		//float activityDuration=(to.getTime()-from.getTime())/3600000;
		rateActivity=(dureeMandateInHours/dureeActiviteDemandeeInHours)*100;
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(0);
		formatter.setRoundingMode(RoundingMode.HALF_UP); 
		Float formatedFloat = new Float(formatter.format(rateActivity));
		return formatedFloat;
		}
	}
	@Override
	public float satisfactionMsgRatio(int resID, String dateFrom, String dateTo) {
		List<Mandate>l=new ArrayList<>();
		//Ressource R= em.find(Ressource.class, resID);
		int aa=0;
		long countAll=0;
		long countSat=0;
		float ratio;
		
		l=oneResActivities(resID, dateFrom, dateTo);
		for(Mandate mand : l){
		
		TypedQuery<Long> countAllQuery=em.createQuery("select count(m) from Message m where m.project = :P AND m.id_reciever = :r AND (m.fromTo = :RR OR m.fromTo = :CR)",Long.class);
		countAllQuery.setParameter("P",mand.getProject() );
		countAllQuery.setParameter("r",resID);
		countAllQuery.setParameter("CR",fromToDirection.Client_Ressource);
		countAllQuery.setParameter("RR",fromToDirection.Ressource_Ressource);
		countAll+=countAllQuery.getSingleResult();
		
		TypedQuery<Long> countSatQuery=em.createQuery("select count(m) from Message m where m.project = :P AND m.id_reciever = :r AND (m.fromTo = :RR OR m.fromTo = :CR) AND m.messageType = :mt",Long.class);
		countSatQuery.setParameter("P",mand.getProject() );
		countSatQuery.setParameter("r",resID);
		countSatQuery.setParameter("CR",fromToDirection.Client_Ressource);
		countSatQuery.setParameter("RR",fromToDirection.Ressource_Ressource);
		countSatQuery.setParameter("mt",MessageType.Satisfaction);
		countSat+=countSatQuery.getSingleResult();
		
		}
		
		if(countAll==0){
			return 404;
		}
		else{
		
		ratio=((float)countSat/(float)countAll)*100;
		
		
		
		System.out.println(ratio);
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(0);
		formatter.setRoundingMode(RoundingMode.HALF_UP); 
		Float formatedFloat = new Float(formatter.format(ratio));
		return formatedFloat;
		}
		
		
	}


}
