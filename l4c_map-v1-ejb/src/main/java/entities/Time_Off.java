//package entities;
//
//import java.io.Serializable;
//import java.sql.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//import pk.TimeOffPk;
//
//@Entity
//public class Time_Off implements Serializable{
//	@EmbeddedId	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private TimeOffPk timeOffPk ;
//	private Date dateBegin ;
//	private Date dateEnd ;
//	private int Duration ;
//	@OneToOne
//	private Responsable responsable ;
//	
//
//	
//	public Date getDateBegin() {
//		return dateBegin;
//	}
//	public void setDateBegin(Date dateBegin) {
//		this.dateBegin = dateBegin;
//	}
//	public Date getDateEnd() {
//		return dateEnd;
//	}
//	public void setDateEnd(Date dateEnd) {
//		this.dateEnd = dateEnd;
//	}
//	public int getDuration() {
//		return Duration;
//	}
//	public void setDuration(int duration) {
//		Duration = duration;
//	}
//	public Time_Off() {
//		// TODO Auto-generated constructor stub
//	}
//
//}
