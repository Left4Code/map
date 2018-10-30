package BusinessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import entities.Mandate;
import entities.Ressource;

import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;   
@Stateless
public class BusinessReports implements IBusinessReports {
	@PersistenceContext(unitName="l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public void testDate() {
		//Date d= new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		System.out.println(dateFormat.format(date));
				
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		dtf.format(now);*/
		
	}

	@Override
	public List<Mandate>  AddActivityReport(int resourceID) {
		Ressource R= em.find(Ressource.class, resourceID);
		TypedQuery<Mandate> f=em.createQuery("select m from Mandate m where m.ressource = :R",Mandate.class);
		f.setParameter("R", R);
		List<Mandate> l =f.getResultList();
		//Mandate M =f.getSingleResult();
		System.out.println("------------------------findQuery niv --------------------------");
		/*for(Mandate m:l){
			System.out.println("id=  "+m.getMandatepk()+"  here ="+m.getRessource().getLastname());
			}*/
		return l;
		
		}

}
