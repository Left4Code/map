package entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import service.SqlDateAdapter;

@Entity
@XmlRootElement
public class Test implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTest;
	private String name;
	private String specialty;
	private int mark;
	private Date dateOfPassing;
	private int difficulty;
	@ManyToOne
	@JoinColumn
	private Responsable responsable;
	
	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", name=" + name + ", specialty=" + specialty + ", mark=" + mark
				+ ", dateOfPassing=" + dateOfPassing + ", difficulty=" + difficulty + ", responsable=" + responsable
				+ "]";
	}
	@XmlTransient
	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	@XmlAttribute
	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
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
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@XmlElement
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@XmlElement
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateOfPassing() {
		return dateOfPassing;
	}

	public void setDateOfPassing(Date dateOfPassing) {
		this.dateOfPassing = dateOfPassing;
	}

	@XmlElement
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Test() {
		// TODO Auto-generated constructor stub
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
		Test other = (Test) obj;
		if (idTest != other.idTest)
			return false;
		return true;
	}
}