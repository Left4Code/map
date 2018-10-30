package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Applicant;
import service.ApplicantServiceLocal;

@Stateless
@Path("inscription")
public class InscriptionRessource {
	@EJB
	ApplicantServiceLocal service ;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addApplicant(Applicant A) {
		int returnid = service.insertApplicant(A);
		if (returnid != 0)
			return Response.status(Status.ACCEPTED).entity(Integer.toString(returnid)).build();
		return Response.status(Status.PRECONDITION_FAILED).entity("Error :Illegal Data").build();
	}

}
