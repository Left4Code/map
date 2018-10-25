package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project implements Serializable {
	@Id
	private int idProject;
	private String name;
	private Date dateBegin;
	private Date dateEnd;
	private String adresse;
	private int nbRessources;
	private int nbRessourcesLevio;
	private String picture;
	@OneToMany(mappedBy="project",cascade=CascadeType.REMOVE)
	private List<Mandate> listemandate ;

	@OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
	private List<Request> requests;

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbRessources() {
		return nbRessources;
	}

	public void setNbRessources(int nbRessources) {
		this.nbRessources = nbRessources;
	}

	public int getNbRessourcesLevio() {
		return nbRessourcesLevio;
	}

	public void setNbRessourcesLevio(int nbRessourcesLevio) {
		this.nbRessourcesLevio = nbRessourcesLevio;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProject;
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
		Project other = (Project) obj;
		if (idProject != other.idProject)
			return false;
		return true;
	}

	public List<Mandate> getListemandate() {
		return listemandate;
	}

	public void setListemandate(List<Mandate> listemandate) {
		this.listemandate = listemandate;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Project() {
		// TODO Auto-generated constructor stub
	}

}