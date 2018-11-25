package ressource;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.ISkillsBusiness;
import service.RessourcesServicesLocal;
import entities.Demand_time_off;
import entities.Skills;

@Stateless
@Path("skills")
public class SkillsRessource {
	
	@EJB
	RessourcesServicesLocal local;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherSkills() {

		List<Skills> lst = local.afficherSkills();
		System.out.println();
		return Response.status(Status.OK).entity(lst).build();
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response ajouterCompetences(Skills skill) {
//
//		local.ajouterCompetence(skill);
//		System.out.println("succccc");
//		return Response.status(Status.CREATED).entity("ok").build();
//
//	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response ajouterSkillAuxRessources(@QueryParam(value = "id") int id, Skills skill) {
		if (id != 0 && skill != null) {
			local.ajouterRessourceEtCompetence(id, skill);
			return Response.status(Status.CREATED).entity("ok").build();
		}

		return Response.status(Status.NO_CONTENT).entity("not created").build();
	}//affecte idRessource à la compétence fraichement crée .

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response ajouterCompetence(@QueryParam(value = "R") int idRessource,
			@QueryParam(value = "idSkill") int idSkill) {
		if (idRessource != 0 && idSkill != 0) {
			local.ajouterCompetenceARessource(idRessource, idSkill);
			return Response.status(Status.CREATED).entity("ok").build();
		}
		return Response.status(Status.NO_CONTENT).entity("not created").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response modifierCompetence(Skills skill) {
		if (skill != null) {
			local.modifierCompetence(skill);
			return Response.status(Status.OK).entity("modified").build();
		}
		return Response.status(Status.NOT_MODIFIED).entity("not modified").build();
	}


	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerCompetence(@PathParam(value = "id") int id) {
		if (id != 0) {
			local.supprimerCompetence(id);
			return Response.status(Status.OK).entity("delete done").build();

		}
		return Response.status(Status.NOT_FOUND).entity("this user doesn't exist").build();
	}

}
