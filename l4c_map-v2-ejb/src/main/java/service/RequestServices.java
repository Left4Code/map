package service;

import entities.Request;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class RequestServices implements RequestLocal,RequestRemote {

    @PersistenceContext(unitName="l4c_map-v2-ejb")
    EntityManager em ;

    @Override
    public Request showRequest(int idRequest) {
        return em.find(Request.class,idRequest);
    }

    @Override
    public List<Request> showAllRequest() {
        return em.createQuery("select r from Request r ",Request.class).getResultList();

    }

    @Override
    public String treatRequest(int idRequest, String status) {
        Request request=em.find(Request.class,idRequest);
        if (request!=null){
            request.setStatus(status);
            em.merge(request);
            return "request "+status;
        }

        return "request not found";

    }

    @Override
    public int addRequest(Request request) {
        em.persist(request);
        return request.getIdRequest();
    }

    @Override
    public void editRequest(Request request) {
        em.merge(request);
    }

    @Override
    public void deleteRequest(int idRequest) {
        em.remove(em.find(Request.class,idRequest));
    }


}
