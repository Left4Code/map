package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Applicant;
import entities.User;

@Stateless
public class UserService implements UserServiceLocal {
	@PersistenceContext(unitName = "l4c_map-v1-ejb")
	EntityManager em;

	@Override
	public boolean ifexist(String username, String password) {
		try{
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
		User user = query.setParameter("username", username).getSingleResult() ;
		if(user.getPassword().equals(password))
			return true ;
		}
		catch(NoResultException e){
			
		}
		return false;
	}

	@Override
	public boolean ifexist(String username) {
		try{
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
			User user = query.setParameter("username", username).getSingleResult() ;
			return true ;
		}
			catch(NoResultException e){
				
			}
		return false;
	}

	@Override
	public User getUserByName(String username) {
		try{
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
			User user = query.setParameter("username", username).getSingleResult() ;
			return user ;
		}
			catch(NoResultException e){
				
			}
		return null;
	}

}
