package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.function.LongToIntFunction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Historical_mandate implements Serializable {
	@Id
	private int idHistoricalMandate;
	private BigInteger weight;
	@OneToMany
	private List<Mandate> listeMandate;

	public int getIdHistoricalMandate() {
		return idHistoricalMandate;
	}

	public void setIdHistoricalMandate(int idHistoricalMandate) {
		this.idHistoricalMandate = idHistoricalMandate;
	}

	public BigInteger getWeight() {
		return weight;
	}

	public void setWeight(BigInteger weight) {
		this.weight = weight;
	}

	public List<Mandate> getListeMandate() {
		return listeMandate;
	}

	public void setListeMandate(List<Mandate> listeMandate) {
		this.listeMandate = listeMandate;
	}

	public Historical_mandate() {
		// TODO Auto-generated constructor stub
	}

}
