package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import java.util.List;

import javax.jms.JMSSessionMode;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import enumerator.Role;
import enumerator.TypeContract;
import enumerator.TypeRessource;
import service.SqlDateAdapter;

@XmlRootElement
@Entity
public class Ressource extends User implements Serializable {
	private Date dateDebut;
	private Date dateFin;
	@Enumerated(EnumType.STRING)
	private TypeRessource type;
	protected String specialty;
	protected String businessSector;
	protected float rateSelling;
	
	protected float cost;
	@Enumerated(EnumType.STRING)
	
	protected TypeContract typeContrat;
	protected int seniority;
	
	protected int note;
	protected String cv;
	
	@ManyToMany( cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Skills> skills  = new HashSet<Skills>();
	@OneToMany(mappedBy = "ressource", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	private Set<Mandate> listemandate =  new HashSet<Mandate>();
	@OneToOne(mappedBy = "ressource", cascade = CascadeType.REMOVE)
	private Sponsor sponsor;

	@OneToMany(mappedBy="ressource" ,cascade=CascadeType.REMOVE , fetch=FetchType.EAGER)
	private Set<Demand_time_off> listeDemandesTimeOff = new HashSet<>();

  @XmlTransient
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getBusinessSector() {
		return businessSector;
	}

	public void setBusinessSector(String businessSector) {
		this.businessSector = businessSector;
	}

	public float getRateSelling() {
		return rateSelling;
	}

	public void setRateSelling(float rateSelling) {
		this.rateSelling = rateSelling;
	}

	public float getCost() {
		return cost;
	}

	public Set<Mandate> getListemandate() {
		return listemandate;
	}

	public void setListemandate(Set<Mandate> listemandate) {
		this.listemandate = listemandate;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public TypeContract getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(TypeContract typeContrat) {
		this.typeContrat = typeContrat;
	}
	
	public Ressource() {
		super();
		this.role = Role.Ressource;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public TypeRessource getType() {
		return type;
	}

	public void setType(TypeRessource type) {
		this.type = type;
	}
	
	
	public Set<Skills> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skills> skills) {
		this.skills = skills;
	}


	public Set<Demand_time_off> getListeDemandesTimeOff() {
		return listeDemandesTimeOff;
	}

	public void setListeDemandesTimeOff(Set<Demand_time_off> listeDemandesTimeOff) {
		this.listeDemandesTimeOff = listeDemandesTimeOff;
	}
}