package services.response_services;


import entities.Response;

import javax.ejb.Remote;

@Remote
public interface ResponseRemote {

    int addResponse(Response response);
    void deleteResponse(int idResponse);
    void editResponse(Response response);
}
