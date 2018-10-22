package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Organizational_Chart implements Serializable {
	@Id
	private int idOrganizational_Chart;

	public int getIdOrganizational_Chart() {
		return idOrganizational_Chart;
	}

	public void setIdOrganizational_Chart(int idOrganizational_Chart) {
		this.idOrganizational_Chart = idOrganizational_Chart;
	}

	public Organizational_Chart() {
		// TODO Auto-generated constructor stub
	}

}
