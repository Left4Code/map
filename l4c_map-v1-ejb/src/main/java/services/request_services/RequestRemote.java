package services.request_services;

import entities.Request;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RequestRemote {
       int addRequest(Request request);
     void editRequest(Request request);
     void deleteRequest(int idRequest);
     Request showRequest(int idRequest);
     List<Request> showAllRequest();
     String treatRequest(int idRequest, String status);

}
