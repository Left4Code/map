package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Skills implements Serializable {
	@Id
	private int idSkills;
	private String name;
	private String skillSpecialty;
	private String degree;
	private int experience;
	private String document;
	@ManyToMany 
	private List <Project> projectList= new ArrayList<>();;

	
	public Skills() {
	}
	
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getSpecialty() {
		return skillSpecialty;
	}

	public void setSpecialty(String skillSpecialty) {
		this.skillSpecialty = skillSpecialty;
	}

	@XmlElement
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	@XmlElement
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	@XmlElement
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@XmlAttribute
	public int getIdSkills() {
		return idSkills;
	}

	public void setIdSkills(int idSkills) {
		this.idSkills = idSkills;
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
		Skills other = (Skills) obj;
		if (idSkills != other.idSkills)
			return false;
		return true;
	}

}