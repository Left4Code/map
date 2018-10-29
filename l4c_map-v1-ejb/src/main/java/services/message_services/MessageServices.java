package services.message_services;

import entities.Client;
import entities.Message;
import entities.Project;
import entities.Ressource;
import enumerator.MessageType;

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
        //if the client made a bad status to the ressource we gonna change his score
        if(message.getFromto().equals("client->ressource"))
        {       //message.reciver is the the id of the entity who's the message is directed
            Ressource ressource=em.find(Ressource.class,message.getReciver());
            //now we gonna test the messagetype and its lvl
            if (message.getMessageType()== MessageType.Technical_problem)
            {
                if (ressource.getNote()-message.getLevel()<=0)
                    ressource.setNote(0);
                else
                    ressource.setNote(ressource.getNote()-message.getLevel());

            }
            if (message.getMessageType()== MessageType.Claim)
            {
                if (ressource.getNote()-message.getLevel()<=0)
                    ressource.setNote(0);
                else
                    ressource.setNote(ressource.getNote()-message.getLevel());

            }
            if (message.getMessageType()== MessageType.Satisfaction)
            {
                if (ressource.getNote()+message.getLevel()>=10)
                    ressource.setNote(10);
                else
                    ressource.setNote(ressource.getNote()+message.getLevel());

            }
            em.merge(ressource);


        }
        //if the message is directed to the whole project team ,they will all suffer of the bad score
        else if (message.getFromto().equals("client->project"))
        {
            message.getProject().getListemandate()
                    .forEach(m->
                            {
                                Ressource ressource=m.getRessource();
                                if (message.getMessageType()== MessageType.Technical_problem)
                                {
                                    if (ressource.getNote()-message.getLevel()<=0)
                                        ressource.setNote(0);
                                    else
                                        ressource.setNote(ressource.getNote()-message.getLevel());

                                }
                                if (message.getMessageType()== MessageType.Claim)
                                {
                                    if (ressource.getNote()-message.getLevel()<=0)
                                        ressource.setNote(0);
                                    else
                                        ressource.setNote(ressource.getNote()-message.getLevel());

                                }
                                if (message.getMessageType()== MessageType.Satisfaction)
                                {
                                    if (ressource.getNote()+message.getLevel()>=10)
                                        ressource.setNote(10);
                                    else
                                        ressource.setNote(ressource.getNote()+message.getLevel());

                                }
                                em.merge(ressource);
                            });

        }

        em.persist(message);

        return message.getIdMessage();
    }

}
