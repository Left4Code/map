package entities;

import enumerator.Reaction;

import javax.persistence.*;
import java.io.Serializable;



@Entity
public class Response implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResponse;
    private String contenu;
    private String status;// seen or not
    private int sender;//the id of the sender
    private String who;//to guess the type of the user,so i can search in the right table
    private String reaction;//it get the reaction of the user for the response
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;//the id of the client who made the reaction
    @ManyToOne
    @JoinColumn(name = "id_message")
    private Message message;

    public int getIdMessage() {
        return idResponse;
    }

    public void setIdMessage(int idMessage) {
        this.idResponse = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(int idResponse) {
        this.idResponse = idResponse;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
