package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Responsable extends User implements Serializable {
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
	private List<Time_Off> listeTimeOff;
	@OneToMany(mappedBy = "responsable")
	private List<Request> listeRequest;

	

	public List<Time_Off> getListeTimeOff() {
		return listeTimeOff;
	}

	public void setListeTimeOff(List<Time_Off> listeTimeOff) {
		this.listeTimeOff = listeTimeOff;
	}

	public List<Request> getListeRequest() {
		return listeRequest;
	}

	public void setListeRequest(List<Request> listeRequest) {
		this.listeRequest = listeRequest;
	}

	public List<Test> getListeTest() {
		return listeTest;
	}

	public void setListeTest(List<Test> listeTest) {
		this.listeTest = listeTest;
	}

	public List<Employement_Letter> getListeEmployementLetter() {
		return listeEmployementLetter;
	}

	public void setListeEmployementLetter(List<Employement_Letter> listeEmployementLetter) {
		this.listeEmployementLetter = listeEmployementLetter;
	}

	public List<Arrival> getListeArrival() {
		return listeArrival;
	}

	public void setListeArrival(List<Arrival> listeArrival) {
		this.listeArrival = listeArrival;
	}

	public List<Sponsor> getListeSponsor() {
		return listeSponsor;
	}

	public void setListeSponsor(List<Sponsor> listeSponsor) {
		this.listeSponsor = listeSponsor;
	}

	public List<Meeting> getListeMeeting() {
		return listeMeeting;
	}

	public void setListeMeeting(List<Meeting> listeMeeting) {
		this.listeMeeting = listeMeeting;
	}

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

	}

}
