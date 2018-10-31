package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.MandateBusinessLocale;
import entities.Mandate;
import entities.Project;

@Stateless
@Path("mandate")
public class MandateRessource {

	@EJB
	MandateBusinessLocale mb;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMandates(@QueryParam(value = "p") int projectId, @QueryParam(value = "r") int ressourceId) {
		if ((projectId == 0) && (ressourceId > 0))
			return Response.status(Status.OK).entity(mb.getMandateByResources(ressourceId)).build();
		else if ((projectId > 0) && (ressourceId == 0))
			return Response.status(Status.OK).entity(mb.getMandateByProject(projectId)).build();
		else if ((projectId < 0) && (ressourceId < 0))
			return Response.status(Status.OK).entity(mb.getEndingMandates()).build();
		else if ((projectId > 0) && (ressourceId > 0))
			return Response.status(Status.OK).entity(mb.getCurrentMandate()).build();

		return Response.status(Status.OK).entity(mb.getMandates()).build();
	}

	@GET
	@Path("archive")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArchivedMandate(@QueryParam(value = "p") int projectId,
			@QueryParam(value = "r") int ressourceId) {
		if ((projectId == 0) && (ressourceId > 0))
			return Response.status(Status.OK).entity(mb.getArchivedMandateByRessource(ressourceId)).build();
		else if ((projectId > 0) && (ressourceId == 0))
			return Response.status(Status.OK).entity(mb.getArchivedMandateByProject(projectId)).build();

		return Response.status(Status.OK).entity(mb.getArchivedMandate()).build();
	}

	@PUT
	@Path("searsh/{p}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRessourcesForProject(@PathParam(value = "p") int p) {
		return Response.status(Status.OK).entity(mb.getRessources(p)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addMandates(Mandate m) {
		mb.addMandate(m);
		return Response.status(Status.CREATED).entity("ok").build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteMandate(Mandate m) {
		mb.deleteMandate(m);
		return Response.status(Status.GONE).entity("deleted").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateMandate(Mandate m) {
		if (m == null) {
			mb.stockMandate();
			return Response.status(Status.OK).entity("stocked").build();
		}
		mb.updateMandate(m);
		return Response.status(Status.ACCEPTED).entity("modified").build();

	}

}
