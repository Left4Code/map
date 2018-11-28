package ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Profitability;
import service.ProjectService;



@Stateless
@Path("profitability")
public class ProfitablityRessource {
	@EJB
	ProjectService psl;
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response getAll(){
	List<Profitability> prof = psl.getAllProfitablity();
			return Response.status(Status.OK).entity(prof).build();


	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getProfiByProjet(@PathParam(value="id") int id){
	List<Profitability> prof = psl.getProfitablityByProject(id);
			return Response.status(Status.OK).entity(prof).build();


	}
}
