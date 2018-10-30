package ressource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.validator.RegexValidator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Applicant;
import entities.Demand;
import entities.Test;
import entities.User;
import enumerator.ApplicantState;
import enumerator.Role;
import service.ApplicantServiceLocal;
import service.DemandServiceLocal;
import service.TestServiceLocal;
import utilites.Secured;

@Stateless
@Path("applicant")
public class ApplicantRessource {

	@EJB
	ApplicantServiceLocal service;
	@EJB
	DemandServiceLocal serviceDemand;
	@EJB
	TestServiceLocal serviceTest;

	@Secured({Role.Applicant})
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteApplicant(@PathParam(value = "id") String id) {
		if (service.deleteApplicant(Integer.parseInt(id)))
			return Response.status(javax.ws.rs.core.Response.Status.OK).entity("Removed from Database").build();
		return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("Error :Incorrect ID ,please check")
				.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateApplicant(Applicant applicant) {
		if (service.updateApplicant(applicant.getId(), applicant)) {
			return Response.status(javax.ws.rs.core.Response.Status.OK).entity("Update successful").build();
		}
		return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity("Error :Update unsuccessful").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllApplicant() {
		ArrayList<Applicant> applicants = service.getAllApplicant();
		if (applicants != null)
			return Response.status(javax.ws.rs.core.Response.Status.OK).entity(applicants).build();
		return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity("Error :No Data found").build();
	}

	@GET
	@Path("{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApplicantByCondition(@PathParam(value = "value") String value) {
		String regexNoDigit = ".\\D";
		String regexDigit = ".\\d";
		Pattern patternNoDigit = Pattern.compile(regexNoDigit);
		Pattern patternDigit = Pattern.compile(regexDigit);
		Matcher matcherNoDigit = patternNoDigit.matcher(value);
		Matcher matcherDigit = patternDigit.matcher(value);
		Applicant applicant = new Applicant();
		if (value.equals("Waiting") || value.equals("Applicant_Being_Recruted")) {
			return Response.status(Status.OK).entity(service.getApplicantByState(ApplicantState.valueOf(value)))
					.build();
		} else if (!matcherNoDigit.find()) {
			applicant = service.getApplicantById(Integer.parseInt(value));
			if (applicant != null)
				return Response.status(Status.OK).entity(applicant).build();
			else
				return Response.status(Status.NOT_FOUND).entity("Error : not found").build();
		}
		if (!matcherDigit.find()) {
			List<User> listeApplicant = service.getApplicantByName(value.trim());
			if (listeApplicant.size() != 0)
				return Response.status(Status.OK).entity(listeApplicant).build();
			else
				return Response.status(Status.NOT_FOUND).entity("Error : not found").build();
		}

		return Response.status(Status.NOT_FOUND).entity("Error : not found").build();
	}

	@POST
	@Path("{idApplicant}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response addDemand(@PathParam(value = "idApplicant") String idApplicant, Demand demand) {
		System.out.println("Demand");
		int idDemand = serviceDemand.insertDemand(demand, Integer.parseInt(idApplicant));
		if (idDemand != 0)
			return Response.status(Status.ACCEPTED).entity(Integer.toString(idDemand)).build();
		return Response.status(Status.BAD_REQUEST).entity("Error : not accepted ").build();
	}

	@PUT
	@Path("{idApplicant}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response makeTest(@PathParam(value = "idApplicant") int idApplicant, Test test) {
		int mark = serviceTest.makeTest(test, idApplicant);
		if (mark != 0) {
			return Response.status(Status.CREATED).entity(Integer.toString(mark)).build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Error : cannot add this test").build();
	}

}
