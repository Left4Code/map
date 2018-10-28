package ressource;


import entities.Message;
import entities.Project;
import entities.Ressource;
import services.message_services.MessageLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("message")
public class MessageRessources {

    @EJB
    MessageLocal messageLocal;



    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showMessage(@PathParam(value="id")int id)
    {

        Message message =messageLocal.showMessage(id);

        if (message==null)
        {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();

        }
        return Response.status(Response.Status.ACCEPTED).entity(message).build();


    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeMessage(@PathParam(value="id")int id)
    {

        messageLocal.removeMessage(id);


            return Response.status(Response.Status.OK).entity("Message removed").build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRequest(Message message)
    {
        if (message!=null)
        {
            messageLocal.editMessage(message);
            return Response.status(Response.Status.OK).entity("message edited").build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid message").build();
    }

    @GET
    @Path("ressource/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesClient(@PathParam(value="id")int id)
    {
        //get ListProject for tht client
        // test section (need to be changed with the getClientProjects from the client services)

        Ressource ressource=new Ressource();
        List<Project> projects=null;
        //end of test secion
        if (!projects.isEmpty()){
            Map<Integer, List<Message>> messagesMap =messageLocal.getMessagesOfProject(projects);
            if (messagesMap!=null)
                return Response.status(Response.Status.OK).entity(messagesMap).build();

            return Response.status(Response.Status.NO_CONTENT).entity("no messages").build();


        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid message").build();


    }


    @GET
    @Path("client/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesRessource(@PathParam(value="id")int id)
    {
        //get ListProject for tht ressource
        // test section (need to be changed with the getressourceProjects from the client services)

        List<Project> projects = new ArrayList<Project>();
        Project p = new Project();
        p.setIdProject(1);
        projects.add(p);
        //end of test secion
        if (!projects.isEmpty()){
            Map<Integer, List<Message>> messagesMap =messageLocal.getMessagesOfProject(projects);
            if (messagesMap!=null)
                return Response.status(Response.Status.OK).entity(messagesMap).build();

            return Response.status(Response.Status.NO_CONTENT).entity("no messages").build();


        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid message").build();


    }




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRequest(Message message)
    {
        if (message!=null)
        {
              //System.out.println("------------------------------"+message.getContenu()+"/////////////////////////");
            int idm=messageLocal.addMessage(message);
            return Response.status(Response.Status.OK).entity("request Created"+idm).build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid request").build();
    }



}
