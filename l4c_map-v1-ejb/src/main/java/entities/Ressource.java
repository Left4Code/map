
package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Set;
import enumerator.TypeContract;
import enumerator.TypeRessource;
import service.SqlDateAdapter;
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE) 
public class Ressource extends User implements Serializable {
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name="DateBegin")
	private Date dateDebut;
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name="DateEnd")
	private Date dateFin;
	@Enumerated(EnumType.STRING)
	@XmlElement(name="type")
	private TypeRessource type;
	@XmlElement(name="specialty")
	protected String specialty;
	@XmlElement(name="businessSector")
	protected String businessSector;
	@XmlElement(name="rateSelling")
	protected float rateSelling;
	@XmlElement(name="cost")
	protected float cost;
	@Enumerated(EnumType.STRING)
	@XmlElement(name="typeContrat")
	protected TypeContract typeContrat;
	
	@XmlElement(name="seniority")
	protected int seniority;
	@XmlElement(name="note")
	protected int note;
	@XmlElement(name="cv")
	protected String cv;
	
	@ManyToMany(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@XmlElement(name="Skills")
	private Set<Skills> skills  = new HashSet<Skills>(); 
	@OneToMany(mappedBy = "ressource", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@XmlElement(name="ListMandate")
	private Set<Mandate> listemandate =  new HashSet<Mandate>();
	@OneToOne(mappedBy = "ressource", cascade = CascadeType.REMOVE)
	@XmlElement(name="Sponsor")
	private Sponsor sponsor;
	@OneToMany(mappedBy="ressource" ,cascade=CascadeType.REMOVE , fetch=FetchType.EAGER)
	@XmlElement(name="DemandesTimeoff")
	private Set<Demand_time_off> listeDemandesTimeOff = new HashSet<>();
  	protected int note;//will work on this attribut to determinate the competance of the ressource


	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public Ressource() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "Ressource [specialty=" + specialty + ", businessSector=" + businessSector + ", rateSelling="
				+ rateSelling + ", cost=" + cost + ", typeContrat=" + typeContrat + ", seniority=" + seniority
				+ ", note=" + note + ", cv=" + cv + ", skills=" + skills + ", listemandate=" + listemandate
				+ ", sponsor=" + sponsor + " listeDemandesTimeOff="
				+ listeDemandesTimeOff + "]";
	}



}