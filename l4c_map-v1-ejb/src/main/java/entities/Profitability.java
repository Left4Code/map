package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profitability implements Serializable {
	@Id
	private int idProfitability;
	private float profitability;
	private float gain;
	private float lost;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "idProject")
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Profitability() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProfitability() {
		return idProfitability;
	}

	public void setIdProfitability(int idProfitability) {
		this.idProfitability = idProfitability;
	}

	public float getProfitability() {
		return profitability;
	}

	public void setProfitability(float profitability) {
		this.profitability = profitability;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public float getLost() {
		return lost;
	}

	public void setLost(float lost) {
		this.lost = lost;
	}

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
		Profitability other = (Profitability) obj;
		if (idProfitability != other.idProfitability)
			return false;
		return true;
	}

}
