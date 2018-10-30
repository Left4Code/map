package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Test;
import service.TestServiceLocal;

@Stateless
@Path("responsable")
public class ResponsableRessource {

	@EJB
	TestServiceLocal service;
	
	@POST
	@Path("{idResponsable}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
	public Response insertTest(@PathParam(value="idResponsable")String idResponsable ,Test test){
		if(test != null && Integer.parseInt(idResponsable) != 0){
			int idTest = service.insertTest(test,Integer.parseInt(idResponsable));
			return Response.status(Status.ACCEPTED).entity(Integer.toString(idTest)).build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Error : bad request send to server !").build();
	}
	

}
