package services.response_services;

import entities.Response;
import enumerator.Reaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ResponseSerivces implements ResponseLocal, ResponseRemote {

    @PersistenceContext(unitName = "l4c_map-v1-ejb")
    EntityManager em;

    //i can acces the array of response throu the message itself so i dont need to get them from the database

    @Override
    public int addResponse(Response response) {

        em.persist(response);

        return response.getIdMessage();

    }

    @Override
    public void deleteResponse(int idResponse) {

        em.remove(em.find(Response.class, idResponse));
    }

    @Override
    public void editResponse(Response response) {

        em.merge(response);

    }

    @Override
    public void addReaction(Response response, String reaction) {
        response.setReaction(reaction);
        if (reaction.equals("happy")){

        if (response.getClient().getScore()<10)
        response.getClient().setScore(response.getClient().getScore()+1);


        }
        if ( !(response.getClient().getScore()-1 <=0))
            response.getClient().setScore(response.getClient().getScore()-1);

             em.merge(response);

    }


}
