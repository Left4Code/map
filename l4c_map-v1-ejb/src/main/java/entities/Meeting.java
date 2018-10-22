package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enumerator.TypeResult;
import pk.MeetingPk;

@Entity
public class Meeting implements Serializable{
	@EmbeddedId
	private MeetingPk meetingPk ;
	private Date date ;
	@Enumerated(EnumType.STRING)
	private TypeResult typeResult ;
	@ManyToOne
	@JoinColumn(name="idResponsable" ,referencedColumnName="id",insertable=false,updatable=false)
	private Responsable responsable ;
	@ManyToOne
	@JoinColumn(name="idDemand" ,referencedColumnName="idDemand",insertable=false,updatable=false)
	private Demand demand ;
	
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
		Meeting other = (Meeting) obj;
		if (meetingPk == null) {
			if (other.meetingPk != null)
				return false;
		} else if (!meetingPk.equals(other.meetingPk))
			return false;
		return true;
	}

	public MeetingPk getMeetingPk() {
		return meetingPk;
	}

	public void setMeetingPk(MeetingPk meetingPk) {
		this.meetingPk = meetingPk;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeResult getTypeResult() {
		return typeResult;
	}

	public void setTypeResult(TypeResult typeResult) {
		this.typeResult = typeResult;
	}

	public Meeting() {
		// TODO Auto-generated constructor stub
	}

}
