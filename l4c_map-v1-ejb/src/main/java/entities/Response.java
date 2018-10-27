package entities;

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
}
