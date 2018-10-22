package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import enumerator.ApplicantState;

@Entity
@XmlRootElement
public class Applicant extends User implements Serializable {
	private String country;
	private int age;
	@Enumerated(EnumType.STRING)
	private ApplicantState applicantState;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "idDemand")
	private Demand demand;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "idArrival")
	private Arrival arrival;
	@OneToMany(mappedBy = "responsable")
	private List<Sponsor> listeSponsor;

	public Arrival getArrival() {
		return arrival;
	}

	public void setArrival(Arrival arrival) {
		this.arrival = arrival;
	}

	public List<Sponsor> getListeSponsor() {
		return listeSponsor;
	}

	public void setListeSponsor(List<Sponsor> listeSponsor) {
		this.listeSponsor = listeSponsor;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ApplicantState getApplicantState() {
		return applicantState;
	}

	public void setApplicantState(ApplicantState applicantState) {
		this.applicantState = applicantState;
	}

	@Override
	public int hashCode() {
		return 5;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant other = (Applicant) obj;
		if (age != other.age)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	public Applicant() {
		// TODO Auto-generated constructor stub
	}

}