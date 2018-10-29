package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumerator.MandateType;
import pk.MandatePk;

@Entity
public class Mandate implements Serializable{
	@EmbeddedId
	private MandatePk mandatepk ;
	private Date dateBegin ;
	private Date dateEnd ;
	private int duration ;
	private float cost ;
	@Enumerated(EnumType.STRING)
	private MandateType mandateType ;
	@ManyToOne
	@JoinColumn(name="idProject" ,referencedColumnName="idProject",insertable=false,updatable=false)
	private Project project ;
	@ManyToOne
	@JoinColumn(name="idRessource" ,referencedColumnName="id",insertable=false,updatable=false)
	private Ressource ressource ;

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public MandateType getMandateType() {
		return mandateType;
	}

	public void setMandateType(MandateType mandateType) {
		this.mandateType = mandateType;
	}
	

	public MandatePk getMandatepk() {
		return mandatepk;
	}

	public void setMandatepk(MandatePk mandatepk) {
		this.mandatepk = mandatepk;
	}

	public Mandate() {
		
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
}
