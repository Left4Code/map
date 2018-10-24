package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Applicant;
import enumerator.ApplicantState;
import service.ApplicantService;
import service.ApplicantServiceLocal;

@Stateless
@Path("applicant")
public class ApplicantRessource {

	@EJB
	ApplicantServiceLocal service;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String addApplicant(Applicant A) throws NamingException{
		return Integer.toString(service.insertApplicant(A));
	}

}
