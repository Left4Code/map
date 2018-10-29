package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Services.SqlDateAdapter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import enumerator.StateDemandTimeOff;
import service.SqlDateAdapter;

@Entity

@XmlAccessorType(XmlAccessType.NONE) 

@XmlRootElement
public class Demand_time_off implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@XmlAttribute(name="idDemandeTimeOff")
	private int idDemandTimeOff;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name="DateBegin")
	private Date dateBegin;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name="DateEnd")
	private Date dateEnd;
	@XmlElement(name="Duration")
	private int Duration;
	@Enumerated(EnumType.STRING)
	@XmlElement(name="StateDemande")
	private StateDemandTimeOff stateDemandTimeOff;

	@ManyToOne
	@XmlElement(name="idresponsable")
	private Responsable responsable ;

	@ManyToOne
	private Ressource ressource;


	@XmlTransient
	public Ressource getRessource() {
		return ressource;
	}


	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}



	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	@XmlTransient
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDuration() {
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	@Override
	public int hashCode() {
		return 5;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demand_time_off other = (Demand_time_off) obj;
		if (idDemandTimeOff != other.idDemandTimeOff)
			return false;
		return true;
	}

	public int getIdDemandTimeOff() {
		return idDemandTimeOff;
	}

	public void setIdDemandTimeOff(int idDemandTimeOff) {
		this.idDemandTimeOff = idDemandTimeOff;
	}


	public StateDemandTimeOff getStateDemandTimeOff() {
		return stateDemandTimeOff;
	}

	public void setStateDemandTimeOff(StateDemandTimeOff stateDemandTimeOff) {
		this.stateDemandTimeOff = stateDemandTimeOff;
	}

	public Demand_time_off() {

	}


	@Override
	public String toString() {
		return "Demand_time_off [idDemandTimeOff=" + idDemandTimeOff + ", dateBegin=" + dateBegin + ", dateEnd="
				+ dateEnd + ", Duration=" + Duration + ", stateDemandTimeOff=" + stateDemandTimeOff + ", responsable="
				+ responsable + ", ressource=" + ressource + "]";
	}
	

}
