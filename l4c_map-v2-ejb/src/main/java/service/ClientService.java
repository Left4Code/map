package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Client;
import entities.User;

/**
 * Session Bean implementation class ClientService
 */
@Stateless
@LocalBean

public class ClientService implements ClientServiceLocal {

    /**
     * Default constructor. 
     */
    public ClientService() {
        
    }
     
	@PersistenceContext(unitName="l4c_map-v2-ejb")
	EntityManager em;
	@Override
	public void ajouterClient(Client cl) {

		em.persist(cl);
	}

	@Override
	public Boolean supprimerClient(int idClient) {
	Client client = em.find(Client.class, idClient);
		if(client != null){
			em.remove(client);		
			return true ;
		}
		return false ;
	}

	@Override
	public void modifierClient(Client cl) {
		
		Client client =em.find(Client.class,cl.getId());
		client = cl;
		em.merge(client);	
	}

	@Override
	public Client getClient(int id) {
	
			TypedQuery<Client> query=em.createQuery("SELECT c FROM Client c where c.id=:id",Client.class);
			return query.setParameter("id",id).getSingleResult();
			
		
	
	}

		
	}
	

