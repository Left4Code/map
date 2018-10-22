package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enumerator.StateDemandTimeOff;

@Entity
public class Demand_time_off implements Serializable{
	@Id
	private int idDemandTimeOff ;
	private Date dateBegin ;
	private Date dateEnd ;
	private int Duration ;
	@Enumerated(EnumType.STRING)
	private StateDemandTimeOff stateDemandTimeOff ;
	@OneToMany(mappedBy="demandTimeOff")
	private List<Time_Off> listeTimeOff ;
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

}
