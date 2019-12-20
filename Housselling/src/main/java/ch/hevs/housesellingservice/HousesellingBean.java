package ch.hevs.housesellingservice;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;


import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;

@Stateful
public class HousesellingBean implements Houseselling {

	@PersistenceContext(name = "HousesellingPU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	EntityTransaction tx = null;
	
	@Override
	public House getHouse(String HouseDescription, String lastnameOwner) {
		Query query = em.createQuery("FROM House a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", HouseDescription);
		query.setParameter("lastname", lastnameOwner);
		
		House house = (House) query.getSingleResult();
		System.out.println("ID House called from getHouse(): "+house.getId());
		return house;
	}

	@Override
	public List<House> getHouseListFromOwnerLastname(String lastname) {
		return (List<House>) em.createQuery("SELECT c.owners FROM Owner c where c.lastname=:lastname")
				.setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Owner> getOwners() {
		return em.createQuery("FROM Owner").getResultList();
	}

	@Override
	public Owner getOwner(long ownerId) {
		return (Owner) em.createQuery("FROM Owner c where c.id=:id").setParameter("id", ownerId).getSingleResult();

	}

	@Override
	public void transfer(House compteSrc, House compteDest, int montant) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHouse(String houseDescription, String street, int number, Owner owner, Location location) {
		try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HousesellingPU");
		tx = em.getTransaction();
		tx.begin();
		House h = new House();
		h.setDescription(houseDescription);
		h.setStreet(street);
		h.setNumber(number);
		h.setOwner(owner);
		h.setLocation(location);
		em.persist(h);
		tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				tx.rollback();
			}catch(IllegalStateException e1) {
				e1.printStackTrace();
			}catch(SecurityException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void addOwner(String firstname, String lastname) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HousesellingPU");
			tx = em.getTransaction();
			tx.begin();
			Owner o = new Owner();
			o.setFirstname(firstname);
			o.setLastname(lastname);
			em.persist(o);
			tx.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
					tx.rollback();
				}catch(IllegalStateException e1) {
					e1.printStackTrace();
				}catch(SecurityException e1) {
					e1.printStackTrace();
				}
			}
		
	}
	
}
