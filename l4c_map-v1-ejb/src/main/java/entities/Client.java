package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import enumerator.TypeCategory;
import enumerator.TypeClient;

@Entity
public class Client extends User implements Serializable{

	private int nbOfRessource ;
	private int nbOfProjectActive ;
	private String logo ;
	@Enumerated(EnumType.STRING)
	private TypeClient typeClient ;
	@Enumerated(EnumType.STRING)
	private TypeCategory typeCategory ;
	@OneToMany(mappedBy="client")
	private List<Message> listeMessage ;
	
	public int getNbOfRessource() {
		return nbOfRessource;
	}

	public void setNbOfRessource(int nbOfRessource) {
		this.nbOfRessource = nbOfRessource;
	}

	public int getNbOfProjectActive() {
		return nbOfProjectActive;
	}

	public void setNbOfProjectActive(int nbOfProjectActive) {
		this.nbOfProjectActive = nbOfProjectActive;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public TypeClient getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient;
	}

	public TypeCategory getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(TypeCategory typeCategory) {
		this.typeCategory = typeCategory;
	}

	
	public Client() {
		
	}

}
