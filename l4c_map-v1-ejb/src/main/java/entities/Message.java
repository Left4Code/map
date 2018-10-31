package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import enumerator.MessageType;
import enumerator.fromToDirection;

@Entity
public class Message implements Serializable {
	@Id
	private int idMessage;
	private String contenu;
	private String Status;//seen or not
	private Calendar date;

	@Enumerated(EnumType.STRING)
	private MessageType messageType;
  //PARTIE KHALIL
	private int level;// the level of Satisfaction,problem,or reclamation

	private int sender;
	private int reciver; //=0 if the reciver is the whole project group
	private String fromto;//string to specify the sender and the reciver(it may be ressource to client,porject,ressource in the same project...)

	@ManyToOne
	@JoinColumn(name = "id_project")
	private Project project;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "message",cascade = CascadeType.REMOVE)
	private List<Response> responses;

  //Partie ABDOU
	private int id_sender;
	private int id_reciever;
	@Enumerated(EnumType.STRING)
	private fromToDirection fromTo;

	public Message() {
		// TODO Auto-generated constructor stub
	}
	public int getIdMessage() {

		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	

	public int getId_sender() {
		return id_sender;
	}

	public void setId_sender(int id_sender) {
		this.id_sender = id_sender;
	}

	public int getId_reciever() {
		return id_reciever;
	}

	public void setId_reciever(int id_reciever) {
		this.id_reciever = id_reciever;
	}

	public fromToDirection getFromTo() {
		return fromTo;
	}

	public void setFromTo(fromToDirection fromTo) {
		this.fromTo = fromTo;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReciver() {
		return reciver;
	}

	public void setReciver(int reciver) {
		this.reciver = reciver;
	}

	public String getFromto() {
		return fromto;
	}

	public void setFromto(String fromto) {
		this.fromto = fromto;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
}