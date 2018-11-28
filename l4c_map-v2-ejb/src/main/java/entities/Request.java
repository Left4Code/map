package entities;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import service.SqlDateAdapter;

@Entity
public class Request implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRequest;
	private Date dateBegin;
	private Date dateEnd;
	private float cout;
	private String status;
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	@OneToMany(fetch = FetchType.EAGER,mappedBy="request")
	private List<Required_Skills> requiredSkills;
	@ManyToOne
	@JoinColumn(name = "idResponsable")
	private Responsable responsable;

	@ManyToOne
	@JoinColumn(name = "idProject")
	private Project project;



	public List<Required_Skills> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(List<Required_Skills> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
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
		Request other = (Request) obj;
		if (idRequest != other.idRequest)
			return false;
		return true;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Request() {
		// TODO Auto-generated constructor stub
	}

}
