package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;


public class TestQueries {
	public static void populate() {
		EntityTransaction tx = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HousesellingPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
	
			Owner o1 = new Owner("Damian", "Wasmer");
			Location l1 = new Location("Embd", "3926");
			House h1 = new House("Rormatta", 1, o1, "Test", l1);
			tx.commit();
			
			em.persist(h1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
