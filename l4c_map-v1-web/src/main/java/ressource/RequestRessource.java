package ressource;

import entities.Request;
import services.request_services.RequestLocal;
import services.request_services.RequestServices;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("request")
public class RequestRessource {
   private Request request;
    @EJB
    RequestLocal requestLocal;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showRequest(@PathParam(value="id")int id)
    {

        request=requestLocal.showRequest(id);

        if (request==null)
        {
            return Response.status(Response.Status.NOT_FOUND).entity("not found").build();

        }
        return Response.status(Response.Status.ACCEPTED).entity(request).build();


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllRequest()
    {

        List<Request> requestList =requestLocal.showAllRequest();

        if (requestList==null)
        {
            return Response.status(Response.Status.NOT_FOUND).entity("empty").build();

        }
        return Response.status(Response.Status.ACCEPTED).entity(requestList).build();


    }


    @PUT
    @Path("{idR}/{status}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response treatRequest(@PathParam(value="idR")int id,@PathParam(value="status")String status){
        String result=  requestLocal.treatRequest(id,status);
        if (result.equals("request not found"))
        {
            return Response.status(Response.Status.NOT_FOUND).entity("request not found").build();

        }
        return Response.status(Response.Status.OK).entity("ok").build();


    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRequest(Request request)
    {
        if (request!=null)
        {
            //  System.out.println("------------------------------"+request.getCout()+"/////////////////////////");
            int idR=requestLocal.addRequest(request);
            return Response.status(Response.Status.OK).entity("request Created").build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid request").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRequest(Request request)
    {
        if (request!=null)
        {
            //  System.out.println("------------------------------"+request.getCout()+"/////////////////////////");
          requestLocal.editRequest(request);
            return Response.status(Response.Status.OK).entity("request edited").build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid request").build();
    }

    @DELETE
    @Path("{idR}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeRequest(@PathParam(value="idR")int id){
       requestLocal.deleteRequest(id);

            return Response.status(Response.Status.OK).entity("request removed").build();



    }






}
