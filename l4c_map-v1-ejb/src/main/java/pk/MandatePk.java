package pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MandatePk implements Serializable {
	private int idRessource;
	private int idProject;

	public int getIdRessource() {
		return idRessource;
	}

	public void setIdRessource(int idRessource) {
		this.idRessource = idRessource;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProject;
		result = prime * result + idRessource;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MandatePk other = (MandatePk) obj;
		if (idProject != other.idProject)
			return false;
		if (idRessource != other.idRessource)
			return false;
		return true;
	}

	public MandatePk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MandatePk [idRessource=" + idRessource + ", idProject=" + idProject + "]";
	}

	
}
