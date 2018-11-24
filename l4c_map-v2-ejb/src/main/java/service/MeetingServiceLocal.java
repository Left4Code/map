package service;

import javax.ejb.Local;

import entities.Meeting;
import pk.MeetingPk;

@Local
public interface MeetingServiceLocal {
	public MeetingPk insertMeeting(Meeting meeting);
	public boolean updateMeeting(Meeting meeting);
}
