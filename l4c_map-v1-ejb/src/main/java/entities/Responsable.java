package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import enumerator.Role;

@Entity
@XmlRootElement
public class Responsable extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String speciality;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE)
	private List<Meeting> listeMeeting;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE)
	private List<Sponsor> listeSponsor;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE)
	private List<Arrival> listeArrival;
	@OneToMany(mappedBy = "responsable")
	private List<Test> listeTest;
	@OneToMany(mappedBy = "responsable")
	private List<Employement_Letter> listeEmployementLetter;

	@OneToMany(mappedBy = "responsable")
	private List<Request> listeRequest;
  
	@XmlTransient
	public List<Message> getListeMessage() {
		return listeMessage;
	}

	public void setListeMessage(List<Message> listeMessage) {
		this.listeMessage = listeMessage;
	}
	@XmlTransient
	public List<Request> getListeRequest() {
		return listeRequest;
	}

	public void setListeRequest(List<Request> listeRequest) {
		this.listeRequest = listeRequest;
	}
	@XmlTransient
	public List<Test> getListeTest() {
		return listeTest;
	}

	public void setListeTest(List<Test> listeTest) {
		this.listeTest = listeTest;
	}
	@XmlTransient
	public List<Employement_Letter> getListeEmployementLetter() {
		return listeEmployementLetter;
	}

	public void setListeEmployementLetter(List<Employement_Letter> listeEmployementLetter) {
		this.listeEmployementLetter = listeEmployementLetter;
	}
	@XmlTransient
	public List<Arrival> getListeArrival() {
		return listeArrival;
	}

	public void setListeArrival(List<Arrival> listeArrival) {
		this.listeArrival = listeArrival;
	}
	@XmlTransient
	public List<Sponsor> getListeSponsor() {
		return listeSponsor;
	}

	public void setListeSponsor(List<Sponsor> listeSponsor) {
		this.listeSponsor = listeSponsor;
	}
	@XmlTransient
	public List<Meeting> getListeMeeting() {
		return listeMeeting;
	}

	public void setListeMeeting(List<Meeting> listeMeeting) {
		this.listeMeeting = listeMeeting;
	}
	@XmlElement
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Responsable(String speciality) {
		super();
		this.speciality = speciality;
	}

	public Responsable() {
		super();
		this.role = Role.Responsable;
	}

}
