package service;

import javax.ejb.Local;

@Local
public interface UserServiceLocal {
	public boolean ifexist(String username ,String password);
}
