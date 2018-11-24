package ressource;

import java.util.List;

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

import entities.Client;
import entities.Project;
import entities.Ressource;
import entities.Skills;
import service.ProjectServiceLocal;

@Stateless
@Path("project")
public class ProjectRessource {
	@EJB
	ProjectServiceLocal psl;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response addProject(Project pr, @PathParam(value = "id") int id) {
		if (pr.getDateBegin().before(pr.getDateEnd())) {
			psl.ajouterProjet(pr, id);

			return Response.status(Status.CREATED).entity("ajout projet aves succes").build();
		}

		return Response.status(Status.NOT_ACCEPTABLE).entity("la date debut doit etre avant date fin").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getProjet(@PathParam(value = "id") int id) {
		Project project = psl.getProjetById(id);

		return Response.status(Status.OK).entity(project).build();

	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response UpdateProjects(Project pr, @PathParam(value = "id") int id) {
		psl.modifierProjet(pr, id);
		return Response.status(Status.OK).entity("update successful").build();

	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteApplicant(@PathParam(value = "id") int id) {
		if (psl.supprimerProjet(id))
			return Response.status(Status.OK).entity("Projet supprimé avec succes").build();
		return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("ID incorrect ").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response getRessourceBy(@QueryParam(value = "id") int idProject) {
		psl.getSkillsBySpeciality(idProject);
		return Response.status(Status.OK).entity("skills affecter à un projet").build();

	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response CalculateProfitability(@QueryParam(value = "idProject") int idProject) {

		psl.CalculerRentability(idProject);

		return Response.status(Status.CREATED).entity("Profitablité calculé").build();

	}
}
