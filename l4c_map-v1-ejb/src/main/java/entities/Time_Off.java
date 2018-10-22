package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pk.TimeOffPk;

@Entity
public class Time_Off implements Serializable{
	@EmbeddedId
	private TimeOffPk timeOffPk ;
	private Date dateBegin ;
	private Date dateEnd ;
	private int Duration ;
	@ManyToOne
	@JoinColumn(name="idResponsable" ,referencedColumnName="id",insertable=false,updatable=false)
	private Responsable responsable ;
	@ManyToOne
	@JoinColumn(name="idDemandTimeOff" ,referencedColumnName="idDemandTimeOff",insertable=false,updatable=false)
	private Demand_time_off demandTimeOff ;
	
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
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public Time_Off() {
		// TODO Auto-generated constructor stub
	}

}
