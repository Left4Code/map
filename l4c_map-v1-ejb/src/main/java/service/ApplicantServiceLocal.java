package service;

import java.util.ArrayList;

import javax.ejb.Local;

import entities.Applicant;
import enumerator.ApplicantState;

@Local
public interface ApplicantServiceLocal {
	public int insertApplicant(Applicant A) ;
	public boolean deleteApplicant(int idApplicant);
	public boolean updateApplicant(int idApplicant,Applicant A);
	public ArrayList<Applicant> getAllApplicant();
	public Applicant getApplicantById();
	public Applicant getApplicantByName();
	public ArrayList<Applicant> getApplicantByState();
}
