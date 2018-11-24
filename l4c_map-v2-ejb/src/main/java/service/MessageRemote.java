package service;

import entities.Message;
import entities.Project;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface MessageRemote {

    int addMessage(Message message);
    void removeMessage(int idMessage);
    void editMessage(Message message);
    Map<Integer, List<Message>> getMessagesOfProject(List<Project> projects);
    Message showMessage(int idMessage);
}
