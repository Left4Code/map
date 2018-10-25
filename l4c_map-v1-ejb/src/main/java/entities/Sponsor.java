package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pk.SponsorPk;

@Entity
public class Sponsor implements Serializable{
	private Date timeBegin ;
	private Date timeEnd ;
	private String language ;
	private String country ;
	@EmbeddedId
	private SponsorPk sponsorPk; 
	@OneToOne
	@JoinColumn(name="idApplicant" ,referencedColumnName="id",insertable=false,updatable=false)
	private Applicant applicant ;
	@ManyToOne
	@JoinColumn(name="idResponsable" ,referencedColumnName="id",insertable=false,updatable=false)
	private Responsable responsable ;
	@OneToOne
	private Ressource ressource ;
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

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Sponsor() {
		// TODO Auto-generated constructor stub
	}

}
