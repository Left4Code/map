//package pk;
//
//import java.io.Serializable;
//
//import javax.persistence.Embeddable;
//
//@Embeddable
//public class TimeOffPk implements Serializable {
//	private int idDemandTimeOff;
//	private int idResponsable;
//
//	@Override
//	public int hashCode() {
//		return 5;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TimeOffPk other = (TimeOffPk) obj;
//		if (idDemandTimeOff != other.idDemandTimeOff)
//			return false;
//		if (idResponsable != other.idResponsable)
//			return false;
//		return true;
//	}
//
//	public int getIdDemandTimeOff() {
//		return idDemandTimeOff;
//	}
//
//	public void setIdDemandTimeOff(int idDemandTimeOff) {
//		this.idDemandTimeOff = idDemandTimeOff;
//	}
//
//	public int getIdResponsable() {
//		return idResponsable;
//	}
//
//	public void setIdResponsable(int idResponsable) {
//		this.idResponsable = idResponsable;
//	}
//
//	public TimeOffPk() {
//		// TODO Auto-generated constructor stub
//	}
//
//}
