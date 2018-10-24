package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Applicant;
import service.ApplicantServiceLocal;

@Stateless
@Path("applicant")
public class ApplicantRessource {

	@EJB
	ApplicantServiceLocal service;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String addApplicant(Applicant A){
		return Integer.toString(service.insertApplicant(A));
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteApplicant(@PathParam(value="id")String id){
		if(service.deleteApplicant(Integer.parseInt(id)))
			return Response.status(javax.ws.rs.core.Response.Status.OK).
					entity("Removed from Database").build();
		return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).
				entity("Incorrect ID ,please check").build();
	}
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateApplicant(Applicant applicant){
		if(service.updateApplicant(applicant.getId(), applicant)){
			return Response.status(javax.ws.rs.core.Response.Status.FOUND)
					.entity("Update successful").build();
		}
		return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND)
				.entity("Update unsuccessful").build();
	}
	

}
