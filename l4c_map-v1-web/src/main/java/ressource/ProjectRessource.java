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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Client;
import entities.Project;
import services.ProjectServiceLocal;

@Stateless
@Path("project")
public class ProjectRessource {
@EJB
ProjectServiceLocal psl;

@POST
@Consumes(MediaType.APPLICATION_XML)
public Response addProject(Project pr){
	
psl.ajouterProjet(pr);
	

return Response.status(Status.CREATED).entity("ajout projet aves succes").build();

}

@GET
@Produces(MediaType.APPLICATION_XML)
@Path("{id}")
public Response getProjet(@PathParam(value="id") int id){
	Project project = psl.getProjetById(id);

		return Response.status(Status.OK).entity(project).build();


}
@PUT
@Consumes(MediaType.APPLICATION_XML)
public Response UpdateProjects(Project pr){
	 psl.modifierProjet(pr);
		return Response.status(Status.OK).entity("update successful").build();

}

@DELETE
@Path("{id}")
@Consumes(MediaType.TEXT_PLAIN)
public Response deleteApplicant(@PathParam(value="id")String id){
	if(psl.supprimerProjet(Integer.parseInt(id)))
		return Response.status(Status.OK).
				entity("Projet supprimé avec succes").build();
	return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).
			entity("ID incorrect ").build();
}
}
