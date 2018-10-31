package services.message_services;

import entities.Message;
import entities.Project;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface MessageLocal {

    int addMessage(Message message);
    void removeMessage(int idMessage);
    void editMessage(Message message);
    Map<Integer, List<Message>> getMessagesOfProject(List<Project> projects);
    Message showMessage(int idMessage);

}
