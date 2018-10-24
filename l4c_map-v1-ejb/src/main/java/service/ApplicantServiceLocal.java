package service;

import javax.ejb.Local;

import entities.Applicant;
import enumerator.ApplicantState;

@Local
public interface ApplicantServiceLocal {
	public int insertApplicant(Applicant A) ;
	public boolean deleteApplicant(int idApplicant);
	public boolean updateApplicant(int idApplicant,Applicant A);
	public boolean updateStateApplicant(ApplicantState ApplicantState);
}
