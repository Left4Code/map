package service;

import javax.ejb.Local;

import entities.Sponsor;
import pk.SponsorPk;

@Local
public interface SponsorServiceLocal {
	public SponsorPk insertSonspor(Sponsor sponsor);
}
