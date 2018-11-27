package service;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserServiceLocal {
	public boolean ifexist(String username ,String password);
	public boolean ifexist(String username);
	public User getUserByName(String username);
}
