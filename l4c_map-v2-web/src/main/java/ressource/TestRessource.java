package ressource;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Test;
import service.TestServiceLocal;

@Stateless
@Path("test")
public class TestRessource {
	@EJB
	TestServiceLocal service ;
	
	@GET
	@Path("{idApplicant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTestByApplicant(@PathParam(value="idApplicant")String idApplicant){
		Set<Test> listetest = service.getTestByApplicant(Integer.parseInt(idApplicant));
		if(listetest != null){
			return Response.status(Status.OK).entity(listetest).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTest() {
		ArrayList<Test> tests = new ArrayList<>() ;
		tests = service.getAllTest();
		if(tests != null) {
			return Response.status(Status.OK).entity(tests).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
