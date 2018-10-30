//package BusinessLayer;
//
//import java.math.RoundingMode;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import entities.Mandate;
//import entities.Ressource;
//import entities.Skills;
//@Stateless
//public class SkillsBusiness implements ISkillsBusiness{
//	@EJB
//	private IActivityReportBusiness AReport ;
//	
//	@PersistenceContext(unitName="l4c_map-v1-ejb")
//	EntityManager em;
//
//	@Override
//	public List<List<Object>> getRatioSkills() {
//		float skillRatio=0;
//		long countAll=0;
//		
//		List<List<Object>> AllList =new ArrayList<>();
//		List<Ressource> resList=new ArrayList<>();
//		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
//		formatter.setMaximumFractionDigits(2);
//		formatter.setMinimumFractionDigits(0);
//		formatter.setRoundingMode(RoundingMode.HALF_UP); 
//		
//		
//		TypedQuery<Object[]> countPerSpec=em.createQuery("select s.specialty,count(s) from Skills s  GROUP BY s.specialty",Object[].class);
//		TypedQuery<Long> countAllQuery=em.createQuery("select count(r) from Ressource r ",Long.class);
//		countAll=countAllQuery.getSingleResult();
//		for(Object[] o : countPerSpec.getResultList()){
//			List<Object> perSkill =new ArrayList<>();
//			List<Ressource> r=new ArrayList<>();
//			float rateActivityAllRes=0;
//			int avg=0;
//			TypedQuery<Ressource> resQuery=em.createQuery("select s.ressource from Skills s where s.specialty = :spec",Ressource.class);
//			resQuery.setParameter("spec", o[0]);//tableau d'objet
//			resList=resQuery.getResultList();
//			for(Ressource res:resList)
//			{	
//				avg++;
//				rateActivityAllRes+=AReport.rateActivtyOneRes(res.getId(), null, null);
//				if(AReport.rateActivtyOneRes(res.getId(), null, null)<=30){
//					r.add(em.find(Ressource.class,res.getId()));
//				}
//			}
//			
//			rateActivityAllRes=rateActivityAllRes/avg;
//			long o1=(long)o[1];
//			skillRatio=(float)o1/(float)countAll;
//			
//			String act="resourcesActivity : "+new Float(formatter.format(rateActivityAllRes))+"%";
//			
//			String resnb="ressources : "+new Float(formatter.format(skillRatio*100))+"%";		
//			perSkill.add((String)o[0]);
//			perSkill.add(resnb);
//			perSkill.add(act);
//			if(!(r.isEmpty())){
//				String lower="Activity Ressources lower than 30% :";
//				perSkill.add(lower);
//				perSkill.add(r);
//			}
//			AllList.add(perSkill);
//		}
//		
//		
//		return AllList;
//	}
//
//}
