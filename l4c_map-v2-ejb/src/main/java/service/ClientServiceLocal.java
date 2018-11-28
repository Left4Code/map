package service;

import java.util.List;

import javax.ejb.Local;

import entities.Client;
import entities.User;

@Local
public interface ClientServiceLocal {
	
	public void ajouterClient (Client cl);
	public Boolean supprimerClient (int idClient);
	public void modifierClient (Client cl);
	public Client getClient(int id);
	public List<Client> getAllClient();


}
