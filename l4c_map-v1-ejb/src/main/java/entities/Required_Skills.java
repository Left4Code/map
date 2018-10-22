package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Required_Skills implements Serializable{
	@Id
	private int idRequired_Skills ;
	private int experience ;
	private String education ;
	@ManyToOne
	@JoinColumn(name="idRequest")
	private Request request ;

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
		Required_Skills other = (Required_Skills) obj;
		if (idRequired_Skills != other.idRequired_Skills)
			return false;
		return true;
	}

	public int getIdRequired_Skills() {
		return idRequired_Skills;
	}

	public void setIdRequired_Skills(int idRequired_Skills) {
		this.idRequired_Skills = idRequired_Skills;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Required_Skills() {
		// TODO Auto-generated constructor stub
	}

}
