package service;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Applicant;
import entities.Responsable;
import entities.Sponsor;
import pk.SponsorPk;

@Stateless
public class SponsorService implements SponsorServiceLocal {
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public SponsorPk insertSonspor(Sponsor sponsor) {
		try {
			if (sponsor.getApplicant() != null && sponsor.getResponsable() != null) {
				Applicant applicant = em.find(Applicant.class, sponsor.getApplicant().getId());
				Responsable responsable = em.find(Responsable.class, sponsor.getResponsable().getId());
				sponsor.setApplicant(applicant);
				sponsor.setResponsable(responsable);
				if (applicant != null && responsable != null) {
					sponsor.getSponsorPk().setIdApplicant(applicant.getId());
					sponsor.getSponsorPk().setIdResponsable(responsable.getId());
					em.persist(sponsor);
					return sponsor.getSponsorPk();
				}
				return null;
			}
		} catch (EntityExistsException e) {
			System.out.println("this object exist already");
		}
		return null;
	}

}
