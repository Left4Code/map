package ressource;


import entities.Message;
import services.response_services.ResponseLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("response")
public class ResponseRessources {

    @EJB
    ResponseLocal responseLocal;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResponse(entities.Response response)
    {
        if (response!=null)
        {
            //System.out.println("------------------------------"+response.getContenu()+"/////////////////////////");
            int idr=responseLocal.addResponse(response);
            return Response.status(Response.Status.OK).entity("response Created"+idr).build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid response").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeResponse(@PathParam(value="id")int id)
    {

        responseLocal.deleteResponse(id);


        return Response.status(Response.Status.OK).entity("response removed").build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editResponse(entities.Response response)
    {
        if (response!=null)
        {
            responseLocal.editResponse(response);
            return Response.status(Response.Status.OK).entity("response edited").build();

        }
        return Response.status(Response.Status.NO_CONTENT).entity("invalid response").build();
    }


}
