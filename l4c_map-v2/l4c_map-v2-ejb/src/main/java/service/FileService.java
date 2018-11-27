package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.File;

@Stateless
public class FileService implements FileServiceLocal{
	@PersistenceContext(unitName = "l4c_map-v2-ejb")
	EntityManager em;

	@Override
	public int insertFile(File F) {
		em.persist(F);
		return F.getId();
	}
	
}
