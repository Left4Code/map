package service;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Demand;
import entities.Meeting;
import entities.Responsable;
import pk.MeetingPk;

@Stateless
public class MeetingService implements MeetingServiceLocal {
	@PersistenceContext(unitName = "l4c_map-v2-ejb")
	EntityManager em;

	@Override
	public MeetingPk insertMeeting(Meeting meeting) {
		try {
			if (meeting != null) {
				Demand demand = em.find(Demand.class, meeting.getDemand().getIdDemand());
				Responsable responsable = em.find(Responsable.class, meeting.getResponsable().getId());
				meeting.setResponsable(responsable);
				meeting.setDemand(demand);
				if (demand != null & responsable != null) {
					meeting.getMeetingPk().setIdDemand(demand.getIdDemand());
					meeting.getMeetingPk().setIdResponsable(responsable.getId());
					em.persist(meeting);
					return meeting.getMeetingPk();
				}
				return null;
			}
		} catch (EntityExistsException e) {
			System.out.println("this object exist already ");
		}
		return null;
	}

	@Override
	public boolean updateMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		return false;
	}

}
