package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Sponsor;
import service.SponsorServiceLocal;

@Stateless
@Path("sponsor")
public class SponsorRessource {

	@EJB
	SponsorServiceLocal service ;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response insertSponsor(Sponsor sponsor){
		if(sponsor.getApplicant() != null && sponsor.getResponsable() != null){
			return Response.status(Status.ACCEPTED).entity(service.insertSonspor(sponsor).toString()).build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Error : bad request send to server").build();
	}

}
