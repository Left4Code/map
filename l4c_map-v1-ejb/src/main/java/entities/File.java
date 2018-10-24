package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class File implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date dateBegin;
	private String note;
	@OneToMany
	private List<Test> listeTest;
	@OneToOne
	@JoinColumn(name = "idEmployementLetter")
	private Employement_Letter employementLetter;
	@OneToMany(mappedBy = "file")
	private List<Document> listeDocument;

	public List<Document> getListeDocument() {
		return listeDocument;
	}

	public void setListeDocument(List<Document> listeDocument) {
		this.listeDocument = listeDocument;
	}

	public Employement_Letter getEmployementLetter() {
		return employementLetter;
	}

	public void setEmployementLetter(Employement_Letter employementLetter) {
		this.employementLetter = employementLetter;
	}

	public List<Test> getListeTest() {
		return listeTest;
	}

	public void setListeTest(List<Test> listeTest) {
		this.listeTest = listeTest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

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

}
