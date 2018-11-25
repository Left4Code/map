package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.User;
import enumerator.ApplicantState;
import service.UserServiceLocal;

@Stateless
@Path("user")
public class UserRessource {
	@EJB
	UserServiceLocal service ;
	
	@GET
	@Path("{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByName(@PathParam(value = "value") String value) {
		User user = service.getUserByName(value);
		if(user != null) {
			return Response.status(Status.OK).entity(user).build() ;				
		}
		return Response.status(Status.NOT_FOUND).entity("No user").build() ;
	}
}
