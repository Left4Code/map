package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enumerator.TypeDocument;

@Entity
public class Document implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDocument;
	@Enumerated(EnumType.STRING)
	private TypeDocument documentType;
	private String title;
	private String picture;
	@ManyToOne
	@JoinColumn(name="idFile")
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public TypeDocument getDocumentType() {
		return documentType;
	}

	public void setDocumentType(TypeDocument documentType) {
		this.documentType = documentType;
	}

	public int getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Document() {
		// TODO Auto-generated constructor stub
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
		Document other = (Document) obj;
		if (idDocument != other.idDocument)
			return false;
		return true;
	}

}