
package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enumerator.DemandState;
import service.SqlDateAdapter;

@Entity
@XmlRootElement
public class Demand implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDemand;
	private Date dateDemand;
	@Enumerated(EnumType.STRING)
	private DemandState demandState;
	private String specialty;
	@OneToMany(mappedBy = "demand",fetch=FetchType.EAGER)
	private Set<Meeting> listeMeeting = new HashSet<>();
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "idFile")
	private File file;

	@XmlAttribute
	public int getIdDemand() {
		return idDemand;
	}

	public void setIdDemand(int idDemand) {
		this.idDemand = idDemand;
	}

	@XmlElement(required = true)
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateDemand() {
		return dateDemand;
	}

	public void setDateDemand(Date dateDemand) {
		this.dateDemand = dateDemand;
	}

	@XmlElement(type = DemandState.class, required = true)
	public DemandState getDemandState() {
		return demandState;
	}

	public void setDemandState(DemandState demandState) {
		this.demandState = demandState;
	}

	@XmlElement(required = false)
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Demand() {
		// TODO Auto-generated constructor stub
	}

	@XmlTransient
	public Set<Meeting> getListeMeeting() {
		return listeMeeting;
	}

	public void setListeMeeting(Set<Meeting> listeMeeting) {
		this.listeMeeting = listeMeeting;
	}

	@XmlElement
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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
		Demand other = (Demand) obj;
		if (idDemand != other.idDemand)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demand [idDemand=" + idDemand + ", dateDemand=" + dateDemand + ", demandState=" + demandState
				+ ", specialty=" + specialty + ", listeMeeting=" + listeMeeting + ", file=" + file + "]";
	}

	public Demand(Date dateDemand, DemandState demandState, String specialty, File file) {
		super();
		this.dateDemand = dateDemand;
		this.demandState = demandState;
		this.specialty = specialty;
		this.file = file;
	}

}