package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

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

	private int score;//a score tht determinate the client type (exigant or flexible)

	@OneToMany(mappedBy = "client",cascade = CascadeType.MERGE)
	private List<Response> responseList;
	
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Response> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<Response> responseList) {
		this.responseList = responseList;
	}
}
