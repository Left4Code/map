package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import enumerator.TypeClient;
import enumerator.TypeProject;
import enumerator.typeRessourceDemande;
import services.SqlDateAdapter;

@Entity
@XmlAccessorType(XmlAccessType.NONE) 
@XmlRootElement
public class Project implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProject;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idClient")
	
	private Client client;
	@XmlElement(name="name",required=true)
	private String name;
	@XmlElement(name="dateBegin",required=true)
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date dateBegin;
	@XmlElement(name="dateEnd",required=true)
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	private Date dateEnd;

	@XmlElement(name="adresse",required=true)
	private String adresse;
	@XmlElement(name="nbRessources",required=true)
	private int nbRessources;
	@XmlElement(name="typeProject",required=true)
	@Enumerated(EnumType.STRING)
	private TypeProject typeProject ;
	@XmlElement(name="Budget",required=true)
	private float Budget;
	@XmlElement(name="typeRessourceDemande",required=true)
	@Enumerated(EnumType.STRING)
	private typeRessourceDemande typeRessourceDemande;
	
	@XmlElement(name="nbRessourcesLevio",required=true)
	private int nbRessourcesLevio;
	@XmlElement(name="picture",required=true)
	private String picture;
	@OneToMany(mappedBy="project",cascade=CascadeType.REMOVE)
	private List<Mandate> listemandate ;

	@ManyToMany
	private List<Skills> listeSkills ;
	
	
	public List<Mandate> getListemandate() {
		return listemandate;
	}

	public void setListemandate(List<Mandate> listemandate) {
		this.listemandate = listemandate;
	}

	public List<Skills> getListeSkills() {
		return listeSkills;
	}

	public void setListeSkills(List<Skills> listeSkills) {
		this.listeSkills = listeSkills;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public float getBudget() {
		return Budget;
	}

	public void setBudget(float budget) {
		Budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	@XmlTransient
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	
	public TypeProject getTypeProject() {
		return typeProject;
	}

	public void setTypeProject(TypeProject typeProject) {
		this.typeProject = typeProject;
	}

	public typeRessourceDemande getTypeRessourceDemande() {
		return typeRessourceDemande;
	}

	public void setTypeRessourceDemande(typeRessourceDemande typeRessourceDemande) {
		this.typeRessourceDemande = typeRessourceDemande;
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

	public Project() {
		
	}

	public Project(String name, Date dateBegin, Date dateEnd, String adresse, int nbRessources, int nbRessourcesLevio,
			String picture) {
		super();
		this.name = name;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.adresse = adresse;
		this.nbRessources = nbRessources;
		this.nbRessourcesLevio = nbRessourcesLevio;
		this.picture = picture;
	}

}