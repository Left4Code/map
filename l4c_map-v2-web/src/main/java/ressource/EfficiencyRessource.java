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

import service.IEffeciency;

@Stateless
@Path("efficiencies")
public class EfficiencyRessource {
	@EJB
	IEffeciency IEff;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEfficiency(@QueryParam(value="id") Integer resID){
		List<List<Object>> l=new ArrayList<>();
		l=IEff.efficiencyReport(resID);
		return Response.status(Status.ACCEPTED).entity(l).build();
	}
}
