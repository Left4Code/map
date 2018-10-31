package services.request_services;

import entities.Request;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RequestLocal  {
       int addRequest(Request request);
     void editRequest(Request request);
     void deleteRequest(int idRequest);
     Request showRequest(int idRequest);
     List<Request> showAllRequest();
     String treatRequest(int idRequest, String status);
}
