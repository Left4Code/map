package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enumerator.DemandState;

@Entity
public class Demand implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDemand;
	private Date dateDemand;
	@Enumerated(EnumType.STRING)
	private DemandState demandState;
	private String specialty;
	@OneToMany(mappedBy = "demand")
	private List<Meeting> listeMeeting;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idFile")
	private File file;

	public int getIdDemand() {
		return idDemand;
	}

	public void setIdDemand(int idDemand) {
		this.idDemand = idDemand;
	}

	public Date getDateDemand() {
		return dateDemand;
	}

	public void setDateDemand(Date dateDemand) {
		this.dateDemand = dateDemand;
	}

	public DemandState getDemandState() {
		return demandState;
	}

	public void setDemandState(DemandState demandState) {
		this.demandState = demandState;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Demand() {
		// TODO Auto-generated constructor stub
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
		Demand other = (Demand) obj;
		if (idDemand != other.idDemand)
			return false;
		return true;
	}

}