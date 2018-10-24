package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Arrival implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArrival;
	private Date arrivalDate;
	private Date timeOfTravelling;
	private int flightNumber;
	@OneToOne(mappedBy="arrival")
	private Applicant applicant ;
	@ManyToOne
	private Responsable responsable;

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public int getIdArrival() {
		return idArrival;
	}

	public void setIdArrival(int idArrival) {
		this.idArrival = idArrival;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getTimeOfTravelling() {
		return timeOfTravelling;
	}

	public void setTimeOfTravelling(Date timeOfTravelling) {
		this.timeOfTravelling = timeOfTravelling;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Arrival() {
		
	}

	@Override
	public int hashCode() {
		return 5 ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arrival other = (Arrival) obj;
		if (idArrival != other.idArrival)
			return false;
		return true;
	}
}
