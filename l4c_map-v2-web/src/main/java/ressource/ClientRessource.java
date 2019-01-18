package ressource;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Client;
import entities.User;
import service.ClientService;
import service.ClientServiceLocal;
 
@Stateless
@Path("client")
public class ClientRessource  {
	@EJB
	ClientServiceLocal csl;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addClient(Client c){
		
	csl.ajouterClient(c);
		

	return Response.status(Status.CREATED).entity("ajout").build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
    public Response getClientbyId(@PathParam(value="id") int id){
		Client client = csl.getClient(id);
	
			return Response.status(Status.OK).entity(client).build();
	

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
    public Response getClientAll(){
	List<Client> client = csl.getAllClient();
	
			return Response.status(Status.OK).entity(client).build();
	

	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateClient(Client cl){
		 csl.modifierClient(cl);
	
			return Response.status(Status.OK).entity("update successful").build();

	}
	
	@DELETE
    @Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteApplicant(@PathParam(value="id")String id){
		if(csl.supprimerClient(Integer.parseInt(id)))
			return Response.status(Status.OK).
					entity("Client supprimé avec succes").build();
		return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).
				entity("ID incorrect ").build();
	}
}
