package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import service.SqlDateAdapter;
@Entity
@XmlRootElement
public class Arrival implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArrival;
	private Date arrivalDate;
	private int flightNumber;
	@OneToOne(mappedBy="arrival")
	private Applicant applicant ;
	@ManyToOne(fetch=FetchType.LAZY)
	private Responsable responsable;
	
	@XmlElement
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	@XmlTransient
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	@XmlAttribute
	public int getIdArrival() {
		return idArrival;
	}

	public void setIdArrival(int idArrival) {
		this.idArrival = idArrival;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	@XmlElement
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
