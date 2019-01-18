package service;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import entities.File;

@Local
public interface FileServiceLocal {
	public int insertFile(File F);
}
