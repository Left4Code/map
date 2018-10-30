package pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MeetingPk implements Serializable {
	private int idDemand;
	private int idResponsable;

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
		MeetingPk other = (MeetingPk) obj;
		if (idDemand != other.idDemand)
			return false;
		if (idResponsable != other.idResponsable)
			return false;
		return true;
	}

	public int getIdDemand() {
		return idDemand;
	}

	public void setIdDemand(int idDemand) {
		this.idDemand = idDemand;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	public MeetingPk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MeetingPk [idDemand=" + idDemand + ", idResponsable=" + idResponsable + "]";
	}

}
