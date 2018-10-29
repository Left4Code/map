package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class File implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date dateBegin;
	private String note;
	@OneToMany(fetch=FetchType.EAGER)
	private Set<Test> listeTest = new HashSet<>();
	@OneToOne
	@JoinColumn(name = "idEmployementLetter")
	private Employement_Letter employementLetter;
	@OneToMany(mappedBy = "file",fetch=FetchType.EAGER)
	private Set<Document> listeDocument = new HashSet<>();

	@XmlTransient
	public Set<Document> getListeDocument() {
		return listeDocument;
	}

	public void setListeDocument(Set<Document> listeDocument) {
		this.listeDocument = listeDocument;
	}
	@XmlElement
	public Employement_Letter getEmployementLetter() {
		return employementLetter;
	}

	public void setEmployementLetter(Employement_Letter employementLetter) {
		this.employementLetter = employementLetter;
	}

	
	public Set<Test> getListeTest() {
		return listeTest;
	}

	public void setListeTest(Set<Test> listeTest) {
		this.listeTest = listeTest;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	@XmlElement
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
		File other = (File) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public File() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "File [dateBegin=" + dateBegin + ", note=" + note + ", listeTest=" + listeTest + ", employementLetter="
				+ employementLetter + ", listeDocument=" + listeDocument + "]";
	}

	public File(Date dateBegin, String note) {
		super();
		this.dateBegin = dateBegin;
		this.note = note;
	}
	

}
