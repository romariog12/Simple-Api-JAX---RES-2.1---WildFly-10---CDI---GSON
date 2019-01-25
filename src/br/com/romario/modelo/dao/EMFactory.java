package br.com.romario.modelo.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Produces;

@RequestScoped
public class EMFactory {
	  private EntityManagerFactory emf = Persistence.createEntityManagerFactory("livroDS"); 
	  private EntityManager em = emf.createEntityManager();;
	  @Produces
	  @RequestScoped
	  public EntityManager getEntityManager() {
		 return em;  
	  }
	
	  public void closeEntityManager(EntityManager em) {
			em.close();
		}
}
