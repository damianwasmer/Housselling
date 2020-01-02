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
	
	//Owner-------------------------------------
	@Override
	public List<Owner> getOwners() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Owner").getResultList();
	}
	
	@Override
	public void addOwner(String firstname, String lastname) {
		// TODO Auto-generated method stub
		Owner o = new Owner();
		o.setFirstname(firstname);
		o.setLastname(lastname);
		em.persist(o);
	}

	@Override
	public Owner getHouseOwner(String firstname, String lastname) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select h.owner from House h where h.firstname = :firstname && h.lastname = :lastname"); 
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		
		
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public Owner getOwnerEntity(String firstname, String lastname) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select o from Owner o where o.firstname = :firstname and o.lastname = :lastname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public void deleteOwner(Owner owner) {
		// TODO Auto-generated method stub
		Owner ownerdelete = em.merge(owner);
		em.remove(ownerdelete);
	}

	@Override
	public void editOwner(Owner owner) {
		// TODO Auto-generated method stub
		em.merge(owner);
	}
	
	
	//House------------------------------------------
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
	public List<House> getHouseListFromOwner(String firstname, String lastname) {
		Query query = em.createQuery("SELECT c.owners FROM Owner c where c.lastname=:lastname && c.firstname=:firstname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		
		return (List<House>) query.getResultList();
	}

	@Override
	public void transfer(House compteSrc, House compteDest, int montant) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHouse(String houseDescription, String street, int number, double price, Location location, Owner owner) {
		House h = new House();
		h.setDescription(houseDescription);
		h.setStreet(street);
		h.setNumber(number);
		h.setPrice(price);
		h.setLocation(location);
		h.setOwner(owner);
		em.persist(h);
	}
	
	//Location---------------------------------------
	@Override
	public List<Location> getLocations() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Location").getResultList();
	}

	@Override
	public void addLocation(String city, String postcode) {
		// TODO Auto-generated method stub
		Location l = new Location();
		l.setCity(city);
		l.setPostCode(postcode);
		em.persist(l);
	}

	@Override
	public long getIdOwner(String lastname) {
		Query query = em.createQuery("FROM Owner o WHERE o.lastname=:lastname");
		query.setParameter("lastname", lastname);
		
		Owner owner = (Owner)query.getSingleResult();
		return owner.getId();
	}

	@Override
	public long getIdLocation(String city) {
		Query query = em.createQuery("FROM Location l WHERE l.city=:city");
		query.setParameter("city", city);
		
		Location location = (Location)query.getSingleResult();
		return location.getId();
	}

	@Override
	public void deleteLocation(Location location) {
		// TODO Auto-generated method stub
		Location locationdelete = em.merge(location);
		em.remove(locationdelete);
	}

	@Override
	public Location getLocation(String city) {
		Query query = em.createQuery("FROM Location c WHERE c.city=:city");
		query.setParameter("city", city);
		
		Location location = (Location)query.getSingleResult();
		System.out.println("ID House called from getLocation(): "+location.getId());
		return location;
		
	}

	@Override
	public Owner getOwnerLastname(String lastname) {
		Query query = em.createQuery("FROM Owner o WHERE o.lastname=:lastname");
		query.setParameter("lastname", lastname);
		
		Owner owner = (Owner)query.getSingleResult();
		System.out.println("ID Owner called from getOwnerLastname(): "+owner.getId());
		return owner;
	}
	

}
