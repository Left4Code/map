package ressource;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.IOtherServices;

@Stateless
@Path("others")
public class OtherRessource {
	@EJB
	IOtherServices IO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNbEmpVsPig(@QueryParam(value="n")int n){
		List<Object> l =new ArrayList<>();
			if(n==1){
				l=IO.getEmployeVsPigiste();
				return Response.status(Status.ACCEPTED).entity(l).build();
			}
			else{
				l=IO.getMandateVsInterMandate();
				return Response.status(Status.ACCEPTED).entity(l).build();
			}
		}
}
