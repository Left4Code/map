package service;

import java.util.ArrayList;
import java.util.Set;

import javax.ejb.Local;

import entities.Test;

@Local
public interface TestServiceLocal {
	public int insertTest(Test test, int idResponsable);

	public boolean updateTest(Test test);

	public boolean removeTest(Test test);

	public int makeTest(Test test, int idApplicant);

	public Set<Test> getTestByApplicant(int idApplicant);
	
	public ArrayList<Test> getAllTest();
}
