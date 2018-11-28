package ressource;

import java.util.ArrayList;

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

import entities.Question;
import service.QuestionServiceLocal;

@Stateless
@Path("question")
public class QuestionRessource {
	@EJB
	QuestionServiceLocal service ;
	
	@POST
	@Path("{idTest}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestionToTest(@PathParam(value = "idTest")String id,Question question) {
		int idQuestion = service.insertQuestion(Integer.parseInt(id),question);
		if(idQuestion != 0) {
			return Response.status(Status.ACCEPTED).entity(Integer.toString(idQuestion) ).build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Error").build();
	}
	
	@GET
	@Path("{idTest}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionByTest(@PathParam(value = "idTest")String id) {
		ArrayList<Question> questions = service.getAllQuestionByTest(Integer.parseInt(id));
		if(questions != null) {
			return Response.status(Status.ACCEPTED).entity(questions).build() ;
		}
		return Response.status(Status.BAD_REQUEST).entity("Error").build();
	}
	@GET
	@Path("{idQuestion}/{choix}")
	public void UpdateTestAndQuestion(@PathParam(value = "idQuestion")String idQuestion,@PathParam(value="choix")String choix) {
		service.CheckAndUpdateQuestionAndTest(Integer.parseInt(idQuestion), choix);
	}
}
