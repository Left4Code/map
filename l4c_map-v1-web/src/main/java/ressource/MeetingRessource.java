package ressource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Meeting;
import pk.MeetingPk;
import service.MeetingServiceLocal;

@Stateless
@Path("meeting")
public class MeetingRessource {
	@EJB
	MeetingServiceLocal service ;
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addMeeting(Meeting meeting){
		MeetingPk meetingPk = service.insertMeeting(meeting);
		if(meetingPk != null){
			if(meetingPk.getIdDemand() != 0 && meetingPk.getIdResponsable() != 0){
				return Response.status(Status.ACCEPTED).entity(meetingPk.toString()).build();
			}
		}	
		return Response.status(Status.BAD_REQUEST).entity("Error : canno't add this meeting ,please check data !").build();
	}

}
