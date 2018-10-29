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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import enumerator.ApplicantState;

@Entity
@XmlRootElement
public class Applicant extends User implements Serializable {
	private String country;
	private int age;
	private int chanceOfSuccess ;
	@Enumerated(EnumType.STRING)
	private ApplicantState applicantState;
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "idDemand")
	private Demand demand;
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "idArrival")
	private Arrival arrival;
	@OneToOne(mappedBy = "applicant")
	private Sponsor sponsor;

	//@XmlElement(required = false)
	@XmlTransient
	public Arrival getArrival() {
		return arrival;
	}

	public void setArrival(Arrival arrival) {
		this.arrival = arrival;
	}

	//@XmlElement(required = false)
	@XmlTransient
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@XmlElement(required = false)
	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	@XmlElement(required = true, name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(required = true, name = "Age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement(required = false, type = ApplicantState.class, name = "State")
	public ApplicantState getApplicantState() {
		return applicantState;
	}

	@Override
	public String toString() {
		return "Applicant [country=" + country + ", age=" + age + ", chanceOfSuccess=" + chanceOfSuccess
				+ ", applicantState=" + applicantState + ", demand=" + demand + "]";
	}

	public void setApplicantState(ApplicantState applicantState) {
		this.applicantState = applicantState;
	}
	
	@XmlElement(name="Chances")
	public int getChanceOfSuccess() {
		return chanceOfSuccess;
	}

	public void setChanceOfSuccess(int chanceOfSuccess) {
		this.chanceOfSuccess = chanceOfSuccess;
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

}
