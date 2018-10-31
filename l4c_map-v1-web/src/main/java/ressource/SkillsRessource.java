package ressource;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import BusinessLayer.ISkillsBusiness;

@Stateless
@Path("skills")
public class SkillsRessource {
	List<List<Object>> l=new ArrayList<>();
	
	@EJB
	private ISkillsBusiness skill;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRatioPerSkill(){
		
		l=skill.getRatioSkills();
		return Response.status(Status.ACCEPTED).entity(l).build();
	}

}
