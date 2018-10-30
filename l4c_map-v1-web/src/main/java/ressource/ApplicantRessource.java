package ressource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import BusinessLayer.BusinessReports;
import BusinessLayer.IActivityReportBusiness;
import BusinessLayer.IBusinessReports;

import entities.Mandate;
@Stateless
@Path("test")
public class ApplicantRessource {
	private List<Mandate> l=new ArrayList<>();
	Mandate m = new Mandate();
	
	@EJB
	private IBusinessReports b;

	@EJB
	private IActivityReportBusiness AReport ;
	
	
	
	@Path("{n}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMand(@PathParam(value="n")int resID){
	System.out.println("hello");
	l=b.AddActivityReport(resID);	
	
	/*if(l==null){
		return Response.status(Status.NOT_FOUND).entity("u missed").build();
	}*/
		return Response.status(Status.ACCEPTED).entity(l).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDuree(@QueryParam(value="m")int i,@QueryParam(value="resID")int resID,@QueryParam(value="from")String from,@QueryParam(value="to")String to){
	System.out.println("hello");
	String activityRateStr="";
	String ratioStr="";
		
		if(i==0	)
		{
		float r=AReport.rateActivtyOneRes(resID, from, to);
			if(r==404)
				{
				activityRateStr="la ressource n est pas employee par levio dans cette durée";
				}
			else{
				activityRateStr=""+r+"%";
				System.out.println(i);
				
				}
			return Response.status(Status.ACCEPTED).entity(activityRateStr).build();
		}
		else if(i==1){
			
			float ratio=AReport.satisfactionMsgRatio(resID, from, to);
			if (ratio==404){
				ratioStr="no messages in this timeline";
			}
			else
			ratioStr=""+ratio+"%";
			return Response.status(Status.ACCEPTED).entity(ratioStr).build();
			
		}
		else
		{
		
				System.out.println(i);
				l=AReport.oneResActivities(resID, from, to);
				System.out.println(l);
				return Response.status(Status.ACCEPTED).entity(l).build();
			
			
			
		}
		
	}
	
	

}
