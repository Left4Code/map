package pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SponsorPk implements Serializable{
	private int idApplicant ;
	private int idResponsable ;
	
	
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
		SponsorPk other = (SponsorPk) obj;
		if (idApplicant != other.idApplicant)
			return false;
		if (idResponsable != other.idResponsable)
			return false;
		return true;
	}

	public int getIdApplicant() {
		return idApplicant;
	}

	public void setIdApplicant(int idApplicant) {
		this.idApplicant = idApplicant;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	@Override
	public String toString() {
		return "SponsorPk [idApplicant=" + idApplicant + ", idResponsable=" + idResponsable + "]";
	}

	public SponsorPk() {
		// TODO Auto-generated constructor stub
	}

}
