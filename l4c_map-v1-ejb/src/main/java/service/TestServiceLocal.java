package service;

import javax.ejb.Local;

import entities.Applicant;
import entities.Test;

@Local
public interface TestServiceLocal {
	public int insertTest(Test test,int idResponsable);
	public boolean updateTest(Test test);
	public boolean removeTest(Test test);
	public int makeTest(Test test ,int idApplicant);
}
