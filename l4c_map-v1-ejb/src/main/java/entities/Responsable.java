package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Responsable extends User implements Serializable {
	private String speciality;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Meeting> listeMeeting = new HashSet<>();
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Sponsor> listeSponsor= new HashSet<>();;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE , fetch=FetchType.EAGER)
	private Set<Arrival> listeArrival= new HashSet<>();;
	@OneToMany(mappedBy = "responsable", fetch=FetchType.EAGER)
	private Set<Test> listeTest= new HashSet<>();;
	@OneToMany(mappedBy = "responsable", fetch=FetchType.EAGER)
	private Set<Employement_Letter> listeEmployementLetter= new HashSet<>();;
	@OneToMany(mappedBy = "responsable", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Message> listeMessage= new HashSet<>();;

	@OneToMany(mappedBy = "responsable", fetch=FetchType.EAGER)
	private Set<Request> listeRequest= new HashSet<>();;
	

	
	
	@XmlTransient
	public Set<Message> getListeMessage() {
		return listeMessage;
	}


	public void setListeMessage(Set<Message> listeMessage) {
		this.listeMessage = listeMessage;
	}

	@XmlElement
	public Set<Request> getListeRequest() {
		return listeRequest;
	}

	public void setListeRequest(Set<Request> listeRequest) {
		this.listeRequest = listeRequest;
	}

	@XmlElement
	public Set<Test> getListeTest() {
		return listeTest;
	}

	public void setListeTest(Set<Test> listeTest) {
		this.listeTest = listeTest;
	}

	@XmlElement
	public Set<Employement_Letter> getListeEmployementLetter() {
		return listeEmployementLetter;
	}

	public void setListeEmployementLetter(Set<Employement_Letter> listeEmployementLetter) {
		this.listeEmployementLetter = listeEmployementLetter;
	}

	@XmlElement
	public Set<Arrival> getListeArrival() {
		return listeArrival;
	}

	public void setListeArrival(Set<Arrival> listeArrival) {
		this.listeArrival = listeArrival;
	}

	@XmlElement
	public Set<Sponsor> getListeSponsor() {
		return listeSponsor;
	}

	public void setListeSponsor(Set<Sponsor> listeSponsor) {
		this.listeSponsor = listeSponsor;
	}

	@XmlElement
	public Set<Meeting> getListeMeeting() {
		return listeMeeting;
	}

	public void setListeMeeting(Set<Meeting> listeMeeting) {
		this.listeMeeting = listeMeeting;
	}

	@XmlElement
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Responsable() {

	}

}
