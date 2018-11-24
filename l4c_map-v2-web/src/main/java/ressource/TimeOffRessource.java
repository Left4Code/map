
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

import org.jboss.resteasy.annotations.Query;

import service.RessourcesServicesLocal;
import entities.Demand_time_off;
import entities.Skills;
@Stateless
@Path("conge")
public class TimeOffRessource {

	
	@EJB
	RessourcesServicesLocal local ;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherConges(@QueryParam(value="id") int id){
			if(id==0){
			List<Demand_time_off> lst = local.afficherDemandesConges();
			System.out.println();
			return Response.status(Status.OK).entity(lst).build();
			}else {
				List<Demand_time_off> lst = local.afficherDemandesCongesPourRessource(id);
				System.out.println();
				return Response.status(Status.OK).entity(lst).build();
			}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerConge(@PathParam(value="id") int id){
		local.supprimerSkills(id);
		return Response.status(Status.OK).entity("ok").build();

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response afficherUnConge(@QueryParam(value="idDemande") int id){
			
			Demand_time_off demande = local.afficherUneDemandeConge(id);
			System.out.println("succ");
			return Response.status(Status.OK).entity(demande).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response ajouterConges(@QueryParam(value="idRessource") int id , Demand_time_off demande){
		
		local.ajouterDemandeConge(id, demande);
			return Response.status(Status.CREATED).entity("ok").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response modifierEtatDemandeConge(@QueryParam(value="idResponsable")int idResponsable ,
			@QueryParam(value="idRessource")int idRessource
			, Demand_time_off demande)
	{
		
		if(idResponsable!=0 && idRessource==0 && demande!=null){
			local.modifierEtatDemandeCongeParResponsable(idResponsable,demande);
			System.out.println("modi");
		return Response.status(Status.OK).entity("Etat modified").build();
	}else if(idResponsable==0 && idRessource!=0 && demande!=null) {
		local.modifierDemandeConge(demande);
		return Response.status(Status.OK).entity("modifié par ressource").build();
		
	}
	return Response.status(Status.NOT_MODIFIED).entity("not modified").build();
		
	}
	

	
	
}
