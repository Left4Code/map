
package ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.DELETE;

import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.RessourcesServicesLocal;
import utilites.Secured;
import entities.Mandate;
import entities.Message;
import entities.Ressource;
import entities.Skills;
import entities.Sponsor;
import enumerator.TypeContract;
@Stateless
@Path("badis")
public class RessourcesService {

	public RessourcesService() {
		// TODO Auto-generated constructor stub
	}
	

	@EJB
	RessourcesServicesLocal local ;
	
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_XML)
	public Response ajouterRessouce(Ressource ressource)
	{
		if (ressource!=null) {
			local.ajouterRessources(ressource);
			local.affecterNoteARessource(ressource.getId());
			return Response.status(Status.CREATED).entity("ok").build();
		}
		return Response.status(Status.NO_CONTENT).entity("not created").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response modifierRessource(Ressource ressource){
		if (ressource!=null) {
			local.modifierRessources(ressource);
			return Response.status(Status.OK).entity("ok").build();
		}
		return Response.status(Status.NOT_MODIFIED).entity("not modified").build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerRessource(@PathParam(value="id") int id){
		if (id!=0){
			local.supprimerRessources(id);
			return Response.status(Status.OK).entity("delete done").build();

		}
		return Response.status(Status.NOT_FOUND).entity("this user doesn't exist").build();

	}
	
	@GET
	@Secured
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response AfficherRessource(@PathParam(value="id")String idd) {
		int id = Integer.parseInt(idd);
		if (id!=0){
			Ressource rc = local.afficherRessources(id);
			return Response.status(Status.OK).entity(rc).build();

		}else if(id==0){
			List<Ressource> lst = local.afficherTousLesRessources();
			System.out.println();
			return Response.status(Status.OK).entity(lst).build();
		}else 
		
		return Response.status(Status.NOT_FOUND).entity("this user doesn't exist").build();
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response AfficherToutesRessource(){
//			
//			List<Ressource> lst = local.afficherTousLesRessources();
//			System.out.println();
//			return Response.status(Status.OK).entity(lst).build();
//
//	
//	}
	

	
	
	//@QueryParam(value="specialty") String specialty,
//	@QueryParam(value="rateSelling") float rateSelling ,
//	@QueryParam(value="cost") float cost ,
//	@QueryParam(value="typeContrat") TypeContract typeContract ,
//	@QueryParam(value="seniority") int seniority ,
//	@QueryParam(value="note") int note ,
//	@QueryParam(value="cv") String cv,
//	@QueryParam(value="mandate") String newMandate ,
//	@QueryParam(value="sponsor") Sponsor sponsor ,
//	@QueryParam(value="message") String message  
//	
//	INSERT INTO `user` (`id`, `lastname`, `name`, `picture`) VALUES (NULL, 'sq', 'sqqs', 'sqsq');
//	INSERT INTO `ressource` (`businessSector`, `cost`, `cv`, `note`, `rateSelling`, `seniority`, `specialty`, `typeContrat`, `id`) VALUES (NULL, '1200', NULL, '11', '11', '11', NULL, 'Employer', '1');
//	INSERT INTO `skills` (`idSkills`, `degree`, `document`, `experience`, `name`, `specialty`, `idRessource`) VALUES ('1', NULL, NULL, '11', NULL, NULL, '1');

	}


