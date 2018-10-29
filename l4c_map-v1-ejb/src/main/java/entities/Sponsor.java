package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import pk.SponsorPk;
import service.SqlDateAdapter;

@Entity
@XmlRootElement
public class Sponsor implements Serializable{
	private Date timeBegin ;
	private Date timeEnd ;
	private String language ;
	private String country ;
	@EmbeddedId
	private SponsorPk sponsorPk = new SponsorPk(); 
	@OneToOne
	@JoinColumn(name="idApplicant" ,referencedColumnName="id",insertable=false,updatable=false)
	private Applicant applicant ;
	@ManyToOne
	@JoinColumn(name="idResponsable" ,referencedColumnName="id",insertable=false,updatable=false)
	private Responsable responsable ;
	@OneToOne
	private Ressource ressource ;
	
	@XmlElement
	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public SponsorPk getSponsorPk() {
		return sponsorPk;
	}

	public void setSponsorPk(SponsorPk sponsorPk) {
		this.sponsorPk = sponsorPk;
	}

	@XmlElement(name="Applicant")
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	@XmlElement(name="Responsable")
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	@XmlElement(name="TimeBegin")
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}
	@XmlElement(name="TimeEnd")
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	@XmlElement(name="Language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Sponsor [timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + ", language=" + language + ", country="
				+ country + ", sponsorPk=" + sponsorPk + ", applicant=" + applicant + ", responsable=" + responsable
				+ ", ressource=" + ressource + "]";
	}

	public Sponsor() {
		// TODO Auto-generated constructor stub
	}

}
