package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumerator.MessageType;
import enumerator.fromToDirection;

@Entity
public class Message implements Serializable {
	@Id
	private int idMessage;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private MessageType messageType;
	private int id_sender;
	private int id_reciever;
	@Enumerated(EnumType.STRING)
	private fromToDirection fromTo;
	
	@ManyToOne
	@JoinColumn(name = "id_project")
	private Project project;

	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}