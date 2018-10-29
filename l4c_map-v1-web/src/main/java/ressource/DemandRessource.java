package ressource;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Demand;
import service.DemandServiceLocal;

@Stateless
@Path("demand")
public class DemandRessource {

	@EJB
	DemandServiceLocal service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDemand() {
		return Response.status(Status.OK).entity(service.getAllDemand()).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateDemand(Demand demand) {
		if (service.updateDemand(demand))
			return Response.status(Status.ACCEPTED).entity("Update Successful !").build();
		return Response.status(Status.NOT_FOUND).entity("Error :Update unsuccessful !").build();
	}
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removeDemand(@PathParam(value="id")String idDemand) {
		if(service.removeDemand(Integer.parseInt(idDemand)))
			return Response.status(Status.ACCEPTED).entity("Removed from database correctly !").build();
		return Response.status(Status.NOT_FOUND).entity("Error : id not found!").build();
	}

}
