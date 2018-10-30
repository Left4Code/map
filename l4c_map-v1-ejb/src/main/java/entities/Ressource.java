package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enumerator.Role;
import enumerator.TypeContract;

@Entity
public class Ressource extends User implements Serializable {
	protected String specialty;
	protected String businessSector;
	protected float rateSelling;
	protected float cost;
	@Enumerated(EnumType.STRING)
	protected TypeContract typeContrat;
	protected int seniority;
	protected int note;
	protected String cv;
	@OneToMany(mappedBy = "ressource", cascade = CascadeType.REMOVE)
	private List<Mandate> listemandate;
	@OneToOne(mappedBy = "ressource", cascade = CascadeType.REMOVE)
	private Sponsor sponsor;
	@OneToMany(mappedBy="ressource")
	private List<Message> listeMessage ;

	public List<Mandate> getListemandate() {
		return listemandate;
	}

	public void setListemandate(List<Mandate> listemandate) {
		this.listemandate = listemandate;
	}

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

}