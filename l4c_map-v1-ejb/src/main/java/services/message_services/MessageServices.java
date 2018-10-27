package services.message_services;

import entities.Message;
import entities.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class MessageServices implements MessageLocal, MessageRemote {

    @PersistenceContext(unitName = "l4c_map-v1-ejb")
    EntityManager em;

    @Override
    public Message showMessage(int idMessage) {
        return em.find(Message.class, idMessage);
    }

    @Override
    public void removeMessage(int idMessage) {
        System.out.println(em.find(Message.class, idMessage)+"   ///////////////"+idMessage);
        em.remove(em.find(Message.class, idMessage));

    }

    @Override
    public void editMessage(Message message) {
        em.merge(message);
    }

    @Override
    public Map<Integer, List<Message>> getMessagesOfProject(List<Project> projects) {
        Map<Integer, List<Message>> messagesMap = new HashMap<Integer, List<Message>>();
        if (projects != null) {
           projects.forEach(p ->{
                        List<Message> messages=em.createQuery("select m from Message m where m.project.id = :projectId", Message.class)
                                .setParameter("projectId", p.getIdProject())
                                .getResultList();
                        if (messages!=null)
                 messagesMap.put(p.getIdProject(),messages );
        }
        );

  }
        return messagesMap;

    }



    @Override
    public int addMessage(Message message) {
        em.persist(message);
        return message.getIdMessage();
    }

}
